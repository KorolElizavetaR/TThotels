package koroler.TThotels.repository;

import koroler.TThotels.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT c FROM City c WHERE c.name = :cityName AND c.country.name = :countryName")
    Optional<City> findByNameAndCountryName(@Param("cityName") String cityName, @Param("countryName") String countryName);
}
