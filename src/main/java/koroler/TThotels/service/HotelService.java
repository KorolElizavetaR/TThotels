package koroler.TThotels.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koroler.TThotels.dto.hotel.HotelDtoRequest;
import koroler.TThotels.dto.hotel.HotelDtoResponse;
import koroler.TThotels.entity.Brand;
import koroler.TThotels.entity.City;
import koroler.TThotels.entity.Hotel;
import koroler.TThotels.mapper.HotelMapper;
import koroler.TThotels.repository.HotelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;
    private final BrandService brandService;
    private final GeoService geoService;
    private final HotelRepository hotelRepository;

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
