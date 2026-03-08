package koroler.TThotels.repository;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.resilience.annotation.Retryable;

import koroler.TThotels.entity.Amenity;

public interface AmenitiesRepository extends JpaRepository<Amenity, Long>{
	@Query("SELECT a FROM Amenity a WHERE a.name = :name")
	Optional<Amenity> findByName(@Param("name") String name);

	@Retryable(includes= {DataIntegrityViolationException.class})
	default public Amenity findOrCreateByName(String name) {
		return this.findByName(name)
	            .orElseGet(() -> {
	            	return this.save(new Amenity(name));
	            });
	}
}
