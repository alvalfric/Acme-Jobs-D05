
package acme.features.authenticated.thread;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.threads.Message;
import acme.entities.threads.Thread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuthenticatedThreadAddUserService implements AbstractUpdateService<Authenticated, Thread> {

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

		request.bind(entity, errors, "moment", "userId");

	}

	@Override
	public void unbind(final Request<Thread> request, final Thread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "creator");
		Collection<Message> messages = entity.getMessages();
		model.setAttribute("messages", messages);
		Collection<Authenticated> users = entity.getUsers();
		model.setAttribute("users", users);
		Integer userId = request.getModel().getInteger("userId");
		model.setAttribute("userId", userId);
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

	@Override
	public void validate(final Request<Thread> request, final Thread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Thread> request, final Thread entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		Integer userId = request.getModel().getInteger("userId");

		Authenticated user = this.repository.findOneAuthenticatedBUserAccountyId(userId);

		entity.getUsers().add(user);
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

	}

}
