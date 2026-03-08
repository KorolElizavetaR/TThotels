package koroler.TThotels.mapper;

import static java.lang.String.format;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import koroler.TThotels.dto.address.AddressDto;
import koroler.TThotels.dto.contact.ContactsDto;
import koroler.TThotels.dto.hotel.HotelDtoGetFullResponse;
import koroler.TThotels.dto.hotel.HotelDtoRequest;
import koroler.TThotels.dto.hotel.HotelDtoResponse;
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

    @Mapping(target = "brand", source="brand.brandName")
    @Mapping(target = "contacts", source = "hotel.contactInfo")
    @Mapping(target = "arrivalTime.checkIn", source = "hotel.checkIn")
    @Mapping(target = "arrivalTime.checkOut", source = "hotel.checkOut")
    @Mapping(target = "amenities", ignore = true)
    HotelDtoGetFullResponse toDtoFull(Hotel hotel);
    
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
    
    @AfterMapping
    default void toAddressDto(@MappingTarget HotelDtoGetFullResponse dto, Hotel hotel) {
    	AddressDto addressDto = dto.getAddress();
    	addressDto.setCity(hotel.getCity().getName());
    	addressDto.setCountry(hotel.getCity().getCountry().getName());
    }
    
    /// TODO: вынести в отдельный маппер
    ContactInfo toEntity(ContactsDto dto);

    @Mapping(target="postcode", source="postCode")
    Hotel.Address mapAddress(AddressDto addressDto);
    
    @Mapping(target="postCode", source="postcode")
    AddressDto mapAddressToDto(Hotel.Address entity);
}
