package koroler.TThotels.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.persistence.EntityNotFoundException;
import koroler.TThotels.dto.hotel.HotelDtoGetFullResponse;
import koroler.TThotels.dto.hotel.HotelDtoRequest;
import koroler.TThotels.dto.hotel.HotelDtoResponse;
import koroler.TThotels.entity.Amenity;
import koroler.TThotels.entity.Brand;
import koroler.TThotels.entity.City;
import koroler.TThotels.entity.Hotel;
import koroler.TThotels.entity.projection.HotelCountProjection;
import koroler.TThotels.mapper.HotelMapper;
import koroler.TThotels.params.GroupingFactor;
import koroler.TThotels.params.HotelParams;
import koroler.TThotels.repository.HotelRepository;
import koroler.TThotels.spec.HotelSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelMapper hotelMapper;
    
    private final BrandService brandService;
    private final GeoService geoService;
    private final AmenitiesService amenitiesService;
    
    private final HotelRepository hotelRepository; 

    @Transactional(readOnly = true)
    public List<HotelDtoResponse> getHotels() {
    	List <Hotel> hotels = hotelRepository.findAll();
    	
        return hotels.stream()
                .map(hotelMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public HotelDtoGetFullResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findByIdFetchEager(id)
                .orElseThrow(() -> new EntityNotFoundException("Hotel not found with id: " + id));
        return hotelMapper.toDtoFull(hotel);
    }
    
    @Transactional(readOnly = true)
    public List<HotelDtoResponse> getHotelByParams(HotelParams params) {
		HotelSpecification spec = new HotelSpecification(params);
		List<Hotel> hotels = hotelRepository.findAll(spec);
		return hotels.stream()
				.map(hotelMapper::toDto)
				.toList();
	}

    @Transactional
    public HotelDtoResponse createHotel(HotelDtoRequest dto) {
        Hotel hotel = hotelMapper.toEntity(dto);

        Brand brand = brandService.findOrCreate(dto.getBrand());
        City city = geoService.findOrCreate(dto.getAddress().getCity(), dto.getAddress().getCountry());
        hotel.setBrand(brand);
        hotel.setCity(city);
        hotel.getContactInfo().setHotel(hotel);
        
        hotel = hotelRepository.save(hotel);
        
        HotelDtoResponse resp = hotelMapper.toDto(hotel);

        return resp;
    }
    
    @Transactional
    public void addAmenitiesToHotel(@RequestBody List<String> amenities, @PathVariable Long id) {
    	Hotel hotel = hotelRepository.findById(id).orElseThrow();
    	Set<Amenity> amenityEntities = amenitiesService.findOrCreateAll(amenities);
    	hotel.setAmenities(amenityEntities);
    	hotelRepository.save(hotel);
    }
    	
    @Transactional(readOnly = true)
    public Map<String, Integer> getHistogram(GroupingFactor groupingFactor) {
        List<HotelCountProjection> projections =
	        switch (groupingFactor) {
	            case brand ->  hotelRepository.countByBrand();
	            case city ->  hotelRepository.countByCity();
	            case country ->  hotelRepository.countByCountry();
	            case amenities ->  hotelRepository.countByAmenity();
	            default -> throw new IllegalArgumentException("Unknown grouping factor: " + groupingFactor);
	        };
        
        Map<String, Integer> result = new HashMap<>();
        for (HotelCountProjection projection : projections) {
            result.put(projection.getGroupingValue(), projection.getHotelCount().intValue());
        }
        return result;
    }
}
