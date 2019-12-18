
package acme.features.authenticated.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.threads.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "tags", "moment", "user");

		model.setAttribute("threadid", request.getModel().getAttribute("threadid"));
		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("confirm", "false");
		} else {
			request.transfer(model, "confirm");
		}

	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Message result = new Message();

		Principal principal = request.getPrincipal();
		Integer id = principal.getAccountId();
		Authenticated au = this.repository.findOneUserByAccountyId(id);

		Integer threadId = request.getModel().getInteger("threadid");

		acme.entities.threads.Thread t = this.repository.findOnethreadById(threadId);

		List<Authenticated> lista = t.getUsers();
		lista.add(au);
		t.setUsers(lista);
		result.setUser(au);
		result.setThread(t);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed;

		CustomisationParameter s = this.repository.findOneCustomisationParameterById();

		String[] spamEn = s.getSpamWordsEn().toString().split(", ");
		String[] spamEs = s.getSpamWordsEs().toString().split(",");

		List<String> spamWord = new ArrayList<String>();
		spamWord.addAll(Arrays.asList(spamEn));
		spamWord.addAll(Arrays.asList(spamEs));

		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "authenticated.message.error.must-confirm");

		if (entity.getTitle() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getTitle()), "title", "authenticated.message.error.spamWords");
		}
		if (entity.getBody() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getBody()), "body", "authenticated.message.error.spamWords");
		}
		if (entity.getTags() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getTags()), "tags", "authenticated.message.error.spamWords");
		}
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	public Boolean existeWordSpam(final List<String> lista, final String text) {
		int spamconter = 0;
		boolean res = false;
		for (String word : lista) {
			if (text.contains(word)) {
				spamconter += 1;
			}
		}
		if (spamconter > 0) {

			res = true;
		}
		return res;
	}
}
