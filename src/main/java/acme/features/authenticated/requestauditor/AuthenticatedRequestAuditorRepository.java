
package acme.features.authenticated.requestauditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestauditors.RequestAuditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestAuditorRepository extends AbstractRepository {

	@Query("select a from RequestAuditor a where a.id=?1")
	RequestAuditor findOneRequestAuditorById(int id);

	@Query("select o from RequestAuditor o")
	Collection<RequestAuditor> findManyAll();

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
}
