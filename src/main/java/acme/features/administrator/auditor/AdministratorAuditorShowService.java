
package acme.features.administrator.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAuditorShowService implements AbstractShowService<Administrator, Auditor> {

	@Autowired
	AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {

		assert request != null;

		return true;

		/*
		 * int audRecId;
		 * Job job;
		 * Auditrecord auditRecord;
		 * Calendar calendar;
		 * Date today;
		 *
		 * audRecId = request.getModel().getInteger("id");
		 * auditRecord = this.repository.findOneAuditrecordById(audRecId);
		 * job = auditRecord.getJob();
		 *
		 * calendar = new GregorianCalendar();
		 * today = calendar.getTime();
		 *
		 * result = job.isFinalMode() && job.getDeadline().after(today);
		 */

	}

	@Override
	public Auditor findOne(final Request<Auditor> request) {

		assert request != null;

		Auditor result;
		int id;

		/*
		 * id = request.getModel().getInteger("id");
		 * result = this.repository.findOneAuditorByUserAccountId(id);
		 */

		id = request.getModel().getInteger("id");
		result = this.repository.findOneAuditorById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStat", "status");

	}
}
