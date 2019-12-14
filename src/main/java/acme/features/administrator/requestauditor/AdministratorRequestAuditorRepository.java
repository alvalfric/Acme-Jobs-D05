
package acme.features.administrator.requestauditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestauditors.RequestAuditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorRequestAuditorRepository extends AbstractRepository {

	@Query("select a from RequestAuditor a where a.id = ?1")
	RequestAuditor findOneRequestAuditorById(int id);

	/*
	 * @Query("select a from RequestAuditor a where a.userAccount.id = ?1")
	 * RequestAuditor findOneRequestAuditorByUserAccountId(int id);
	 */

	@Query("select a from RequestAuditor a where a.statuss = false")
	Collection<RequestAuditor> findManyRequestAuditor();

}
