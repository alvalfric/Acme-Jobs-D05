
package acme.features.sponsor.commercialBanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.customisationParameters.CustomisationParameter;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	// Internal state ---------------------------------------------------------

	@Autowired
	SponsorCommercialBannerRepository repository;

	/* AdministratorCustomisationParameterRepository spamrepository; */


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "slogan", "targetURL", "creditCardNumber", "picture", "ccExpirationDate", "ccValidationNumber");

	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result = new CommercialBanner();

		Principal principal = request.getPrincipal();
		Integer id = principal.getAccountId();
		Sponsor sponsor = this.repository.findOneSponsorBUserAccountyId(id);
		result.setSponsor(sponsor);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		CustomisationParameter s = this.repository.findOneCustomisationParameterById();

		String[] spamEn = s.getSpamWordsEn().toString().split(", ");
		String[] spamEs = s.getSpamWordsEs().toString().split(",");

		List<String> spamWord = new ArrayList<String>();
		spamWord.addAll(Arrays.asList(spamEn));
		spamWord.addAll(Arrays.asList(spamEs));

		if (entity.getPicture() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getPicture()), "picture", "sponsor.commercialBannerr.error.spamWords");
		}
		if (entity.getSlogan() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getSlogan()), "slogan", "sponsor.commercialBannerr.error.spamWords");
		}
		if (entity.getTargetURL() != null) {
			errors.state(request, !this.existeWordSpam(spamWord, entity.getTargetURL()), "targetURL", "sponsor.commercialBannerr.error.spamWords");
		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
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
