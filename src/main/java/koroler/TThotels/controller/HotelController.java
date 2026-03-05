package koroler.TThotels.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import koroler.TThotels.dto.HotelDtoRequest;
import koroler.TThotels.dto.HotelDtoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/property-view")
public class HotelController {
	
	@PostMapping("/hotels")
	public HotelDtoResponse createHotel(@Valid @RequestBody HotelDtoRequest hotel) {
		return null;
	}
}
