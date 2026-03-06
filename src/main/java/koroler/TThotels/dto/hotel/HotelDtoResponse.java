package koroler.TThotels.dto.hotel;

import lombok.Data;

@Data
public class HotelDtoResponse {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String phone;
}
