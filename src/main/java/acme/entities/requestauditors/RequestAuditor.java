
package acme.entities.requestauditors;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class RequestAuditor extends DomainEntity {

	/**
	 *
	 */
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firmm;

	@NotBlank
	private String				responsabilityStatt;

	private boolean				statuss;

	/*
	 * @ManyToMany
	 * private List<Authenticated> users;
	 */

}
