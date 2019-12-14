
package acme.features.administrator.requestauditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestauditors.RequestAuditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorRequestAuditorShowService implements AbstractShowService<Administrator, RequestAuditor> {

	@Autowired
	AdministratorRequestAuditorRepository repository;


	@Override
	public boolean authorise(final Request<RequestAuditor> request) {

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
	public RequestAuditor findOne(final Request<RequestAuditor> request) {

		assert request != null;

		RequestAuditor result;
		int id;

		/*
		 * id = request.getModel().getInteger("id");
		 * result = this.repository.findOneAuditorByUserAccountId(id);
		 */

		id = request.getModel().getInteger("id");
		result = this.repository.findOneRequestAuditorById(id);

		return result;
	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firmm", "responsabilityStatt", "statuss");

	}
}
