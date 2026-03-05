package koroler.TThotels.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalTime;

@Data
public class HotelDtoRequest {
	@NotBlank
    private String name;
	@NotBlank
    private String description;
	@NotBlank
    private String brand;
    private AddressDto address;
    private ContactsDto contacts;
    private ArrivalTimeDto arrivalTime;

    @Data
    public static class AddressDto {
        private Integer houseNumber;
        private String street;
        private String city;
        private String country;
        private String postCode;
    }

    @Data
    public static class ContactsDto {
        private String phone;
        private String email;
    }

    @Data
    public static class ArrivalTimeDto {
        @Schema(example = "14:00")
        private LocalTime checkIn;
        @Schema(example = "12:00")
        private LocalTime checkOut;
    }
}
