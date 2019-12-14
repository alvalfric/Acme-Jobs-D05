
package acme.features.administrator.requestauditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.requestauditors.RequestAuditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/request-auditor/")
public class AdministratorRequestAuditorController extends AbstractController<Administrator, RequestAuditor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorRequestAuditorListPendingService	lisPendingService;

	@Autowired
	private AdministratorRequestAuditorShowService			showService;

	/*
	 * @Autowired
	 * private AdministratorRequestAuditorCreateService createService;
	 */


	// Constructors -----------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_PENDING, BasicCommand.LIST, this.lisPendingService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		/* super.addBasicCommand(BasicCommand.CREATE, this.createService); */
	}

}
