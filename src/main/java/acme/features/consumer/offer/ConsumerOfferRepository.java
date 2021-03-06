
package acme.features.consumer.offer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ConsumerOfferRepository extends AbstractRepository {

	@Query("select o from Offer o where o.id = ?1 and o.deadline>=current_timestamp()")
	Offer findOneById(int id);

	@Query("select o from Offer o where o.ticker = ?1 and o.deadline>=current_timestamp()")
	Offer findOneByTicker(String ticker);

	@Query("select o from Offer o where o.deadline>=current_timestamp()")
	Collection<Offer> findManyAll();

}
