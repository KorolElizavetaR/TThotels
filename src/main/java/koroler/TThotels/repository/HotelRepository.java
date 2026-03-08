package koroler.TThotels.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import koroler.TThotels.entity.Hotel;
import koroler.TThotels.entity.projection.HotelCountProjection;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
	
	@Query("SELECT h FROM Hotel h WHERE h.id = :id")
	@EntityGraph(value = "Hotel.withDetails", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Hotel> findByIdFetchEager(Long id);
	
	@Query("SELECT h.brand.brandName AS groupingValue, COUNT(h) AS hotelCount FROM Hotel h GROUP BY h.brand.brandName")
	List<HotelCountProjection> countByBrand();
	
	@Query("SELECT h.city.name AS groupingValue, COUNT(h) AS hotelCount FROM Hotel h GROUP BY h.city.name")
	List<HotelCountProjection> countByCity();
	
	@Query("SELECT h.city.country.name AS groupingValue, COUNT(h) AS hotelCount FROM Hotel h GROUP BY h.city.country.name")
	List<HotelCountProjection> countByCountry();
	
	@Query("SELECT a.name AS groupingValue, COUNT(DISTINCT h) AS hotelCount FROM Hotel h JOIN h.amenities a GROUP BY a.name")
	List<HotelCountProjection> countByAmenity();
	
}
