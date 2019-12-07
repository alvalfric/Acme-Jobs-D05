
package acme.features.authenticated.thread;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedThreadShowService implements AbstractShowService<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedThreadRepository repository;


	// AbstractShowService<Authenticated, Announcement> interface -------------

	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;
		Principal principal = request.getPrincipal();
		Integer id = request.getModel().getInteger("id");
		Thread result = this.repository.findOneById(id);
		Collection<Authenticated> users = result.getUsers();
		Collection<Integer> userAccounts = new ArrayList<Integer>();
		for (Authenticated user : users) {
			userAccounts.add(user.getUserAccount().getId());
		}

		assert userAccounts.contains(principal.getAccountId());

		return true;
	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
		Collection<Message> messages = entity.getMessages();
		model.setAttribute("messages", messages);

	}

	@Override
	public Thread findOne(final Request<Thread> request) {
		assert request != null;

		Thread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		result.getMessages();

		return result;
	}

}
