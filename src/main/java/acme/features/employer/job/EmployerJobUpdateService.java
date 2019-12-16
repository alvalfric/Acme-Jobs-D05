
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "finalMode", "descriptor.description");
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isDuplicated, referenceOK, websiteOk;
		int jobId;
		double dutyPercentage = 0;
		String regexpReference = "^([A-Z0-9]{1,7})-([A-Z0-9]{1,7})$";
		String regexpUrl = "^(https?:\\/\\/)?(www\\.)?([a-zA-Z0-9]+(-?[a-zA-Z0-9])*\\.)+[\\w]{2,}(\\/\\S*)?$";

		if (entity.isFinalMode()) {
			for (Duty duty : entity.getDescriptor().getDuties()) {
				dutyPercentage += duty.getPercentage();
				System.out.println(duty.getPercentage());
			}

			if (dutyPercentage < 100.0) {
				System.out.println("menor");
				errors.state(request, !(dutyPercentage < 100.0), "finalMode", "duties menor de 100");
				request.getModel().setAttribute("finalMode", false);
			} else if (dutyPercentage > 100.0) {
				System.out.println("mayor");
				errors.state(request, !(dutyPercentage > 100.0), "finalMode", "duties mayor de 100");
				request.getModel().setAttribute("finalMode", false);
			}
		}

		if (this.repository.findOneJobByReference(entity.getReference()) != null) {
			jobId = this.repository.findOneJobByReference(entity.getReference()).getId();
			isDuplicated = jobId == entity.getId();
			errors.state(request, isDuplicated, "reference", "reference duplicated");
		}

		referenceOK = entity.getReference().matches(regexpReference);
		errors.state(request, referenceOK, "reference", "error reference pattern", regexpReference);

		if (!errors.hasErrors("reference")) {
			errors.state(request, !entity.getReference().isEmpty(), "reference", "reference empty");
		}
		if (!errors.hasErrors("title")) {
			errors.state(request, !entity.getTitle().isEmpty(), "title", "title empty");
		}
		if (!errors.hasErrors("deadline")) {
			errors.state(request, entity.getDeadline() != null, "deadline", "deadline empty");
		}
		if (!errors.hasErrors("salary")) {
			errors.state(request, entity.getSalary() != null, "salary", "salary empty");
		}
		if (!entity.getMoreInfo().isEmpty()) {
			websiteOk = entity.getMoreInfo().matches(regexpUrl);
			errors.state(request, websiteOk, "website", "error website pattern", regexpUrl);
		}
		if (!errors.hasErrors("descriptor.description")) {
			errors.state(request, !entity.getDescriptor().getDescription().isEmpty(), "descriptor.description", "description empty");
		}
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		double dutyPercentage = 0.0;

		for (Duty duty : entity.getDescriptor().getDuties()) {
			dutyPercentage += duty.getPercentage();
			System.out.println(duty.getPercentage());
		}

		if (entity.isFinalMode()) {
			if (dutyPercentage == 100.0) {
				this.repository.save(entity.getDescriptor());
				this.repository.save(entity);
			} else {
				entity.setFinalMode(false);
			}
		} else {
			this.repository.save(entity.getDescriptor());
			this.repository.save(entity);
		}

	}

	@Override
	public void onSuccess(final Request<Job> request, final Response<Job> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
