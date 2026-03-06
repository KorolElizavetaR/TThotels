package koroler.TThotels.dto.arrival;

import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArrivalTimeDto {
	 @Schema(example = "14:00")
     private LocalTime checkIn;
     @Schema(example = "12:00")
     private LocalTime checkOut;
}
