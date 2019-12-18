
package acme.features.authenticated.thread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.threads.Thread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	@Query("select t from Thread t where t.id = ?1")
	Thread findOneById(int id);

	@Query("select t from Thread t")
	Collection<Thread> findManyAll();

	@Query("select t, count(u) from Thread t join t.users u where u.id = ?1 group by t")
	Collection<Thread> findManyByUserId(int userId);

	@Query("select t from Thread t where t.creator.id = ?1")
	Collection<Thread> findManyByCreatorId(int creatorId);

	@Query("select a from Authenticated a")
	Collection<Authenticated> findAllAuthenticated();

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedBUserAccountyId(int id);
  
}
