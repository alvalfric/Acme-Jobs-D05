
package acme.features.administrator.requestauditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestauditors.RequestAuditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorRequestAuditorListPendingService implements AbstractListService<Administrator, RequestAuditor> {

	// Internal state --------------------------------------------------------------------

	@Autowired
	AdministratorRequestAuditorRepository repository;


	// AbstractListService<Employer, Job> interface -------------------------------------

	@Override
	public boolean authorise(final Request<RequestAuditor> request) {
		// TODO Auto-generated method stub
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmm", "responsabilityStatt", "statuss");
	}

	@Override
	public Collection<RequestAuditor> findMany(final Request<RequestAuditor> request) {
		// TODO Auto-generated method stub
		assert request != null;

		Collection<RequestAuditor> result;

		result = this.repository.findManyRequestAuditor();
		return result;
	}

}
