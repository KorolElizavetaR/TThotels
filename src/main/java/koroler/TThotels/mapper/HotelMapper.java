package koroler.TThotels.mapper;

import static java.lang.String.format;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import koroler.TThotels.dto.hotel.HotelDtoRequest;
import koroler.TThotels.dto.hotel.HotelDtoResponse;
import koroler.TThotels.dto.hotel.HotelDtoRequest.ContactsDto;
import koroler.TThotels.entity.ContactInfo;
import koroler.TThotels.entity.Hotel;

@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "city", ignore = true)
    @Mapping(target="checkIn", source="arrivalTime.checkIn")
    @Mapping(target="checkOut", source="arrivalTime.checkOut")
    @Mapping(target="contactInfo", source="contacts")
    Hotel toEntity(HotelDtoRequest dto);
    
    @Mapping (target = "address", ignore = true)
    @Mapping(target ="phone", source="contactInfo.phone")
    HotelDtoResponse toDto(Hotel entity);
    
    /// "address": "9 Pobediteley Avenue, Minsk, 220004, Belarus",
    @AfterMapping
    default void toAddressString(Hotel hotel, @MappingTarget HotelDtoResponse dto) {
    	Hotel.Address address = hotel.getAddress();
    	String addressFormated = format(
    			"%d %s, %s, %s, %s", 
    			address.getHouseNumber(), 
    			address.getStreet(), 
    			hotel.getCity().getName(), 
    			address.getPostcode(), 
    			hotel.getCity().getCountry().getName());
    	dto.setAddress(addressFormated);
    }
    
    /// TODO: вынести в отдельный маппер
    ContactInfo toEntity(ContactsDto dto);

    @Mapping(target="postcode", source="postCode")
    Hotel.Address mapAddress(HotelDtoRequest.AddressDto addressDto);
}
