
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.threads.Message;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m")
	Collection<Message> findManyAll();

	@Query("select t from Thread t join t.messages m where m.id = ?1 group by t")
	acme.entities.threads.Thread findThreadByMessageId(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneUserByAccountyId(int id);

	@Query("select t from Thread t where t.id = ?1")
	acme.entities.threads.Thread findOnethreadById(int id);

}
