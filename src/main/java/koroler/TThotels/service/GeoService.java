package koroler.TThotels.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koroler.TThotels.entity.City;
import koroler.TThotels.entity.Country;
import koroler.TThotels.repository.CityRepository;
import koroler.TThotels.repository.CountryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GeoService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Transactional
    public City findOrCreate(String cityName, String countryName) {
        return cityRepository.findByNameAndCountryName(cityName, countryName)
                .orElseGet(() -> {
                	Country country = countryRepository.findByName(countryName)
                            .orElseGet(() -> new Country(countryName));
                	City newCity = new City(cityName);
                    newCity.setCountry(country);
                    return cityRepository.save(newCity);
                });
    }
}
