package koroler.TThotels.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import koroler.TThotels.HotelSpecification;
import koroler.TThotels.dto.hotel.HotelDtoGetFullResponse;
import koroler.TThotels.dto.hotel.HotelDtoRequest;
import koroler.TThotels.dto.hotel.HotelDtoResponse;
import koroler.TThotels.entity.Brand;
import koroler.TThotels.entity.City;
import koroler.TThotels.entity.Hotel;
import koroler.TThotels.mapper.HotelMapper;
import koroler.TThotels.params.HotelParams;
import koroler.TThotels.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelMapper hotelMapper;
    
    private final BrandService brandService;
    private final GeoService geoService;
    
    private final HotelRepository hotelRepository;

    @Transactional(readOnly = true)
    public List<HotelDtoResponse> getHotels() {
    	List <Hotel> hotels =  hotelRepository.findAll();
    	log.debug("example hotel address: {}", hotels.getFirst().getAddress());
    	
        return hotelRepository.findAll().stream()
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
}
