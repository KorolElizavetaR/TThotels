package koroler.TThotels.dto.address;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDto {
	private Integer houseNumber;
    private String street;
    @NotBlank(message = "City should not be empty")
    private String city;
    @NotBlank(message = "Country should not be empty")
    private String country;
    private String postCode;
}
