package koroler.TThotels.dto.hotel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import koroler.TThotels.dto.address.AddressDto;
import koroler.TThotels.dto.arrival.ArrivalTimeDto;
import koroler.TThotels.dto.contact.ContactsDto;
import lombok.Data;

@Data
public class HotelDtoRequest {
	@NotBlank
    private String name;
	@Nullable
	@Schema(nullable = true)
    private String description;
	@NotBlank
    private String brand;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;

}
