package koroler.TThotels.dto.hotel;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import koroler.TThotels.dto.address.AddressDto;
import koroler.TThotels.dto.arrival.ArrivalTimeDto;
import koroler.TThotels.dto.contact.ContactsDto;
import lombok.Data;

@Data
public class HotelDtoGetFullResponse {
	private Long id;
	@NotBlank
    private String name;
	@NotBlank
    private String description;
	@NotBlank
    private String brand;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;
    private List<String> amenities;
}
