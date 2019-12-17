
package acme.entities.requestAuditors;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RequestAuditor extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firm;

	@NotBlank
	private String				responsabilityStat;

	@NotBlank
	private UserAccount			userAccount;
}
