
package acme.features.administrator.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuditorListPendingService implements AbstractListService<Administrator, Auditor> {

	// Internal state --------------------------------------------------------------------

	@Autowired
	AdministratorAuditorRepository repository;


	// AbstractListService<Employer, Job> interface -------------------------------------

	@Override
	public boolean authorise(final Request<Auditor> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStat", "status");
	}

	@Override
	public Collection<Auditor> findMany(final Request<Auditor> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Collection<Auditor> result;

		result = this.repository.findManyAuditor();
		return result;
	}

}
