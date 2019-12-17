
package acme.features.authenticated.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedThreadCreateService implements AbstractCreateService<Authenticated, Thread> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AuthenticatedThreadRepository repository;


	@Override
	public boolean authorise(final Request<Thread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");

	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "statement", "skills", "qualifications", "moment", "lastUpdate");
		Collection<Message> messages = entity.getMessages();
		model.setAttribute("messages", messages);
		Collection<Authenticated> users = entity.getUsers();
		model.setAttribute("users", users);
	}

	@Override
	public Thread instantiate(final Request<Thread> request) {
		assert request != null;

		Thread result = new Thread();
		Principal principal = request.getPrincipal();
		Integer id = principal.getAccountId();
		Authenticated creator = this.repository.findOneAuthenticatedBUserAccountyId(id);
		List<Authenticated> users = new ArrayList<Authenticated>();
		Set<Message> messages = new HashSet<Message>();
		result.setCreator(creator);
		result.setUsers(users);
		result.setMessages(messages);
		result.setTitle("Title");

		return result;
	}

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Thread> request, final Thread entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}