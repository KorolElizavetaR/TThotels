package koroler.TThotels.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import koroler.TThotels.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
	
	@Query("SELECT h FROM Hotel h WHERE h.id = :id")
	@EntityGraph(value = "Hotel.withDetails", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Hotel> findByIdFetchEager(Long id);
	
}
