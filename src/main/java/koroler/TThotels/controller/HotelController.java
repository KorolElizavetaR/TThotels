package koroler.TThotels.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import koroler.TThotels.dto.hotel.HotelDtoGetFullResponse;
import koroler.TThotels.dto.hotel.HotelDtoRequest;
import koroler.TThotels.dto.hotel.HotelDtoResponse;
import koroler.TThotels.service.HotelService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/property-view")
public class HotelController {
	
	private final HotelService hotelService;
	
	@GetMapping("/hotels")
	public List<HotelDtoResponse> getHotels() {
		/// TODO
		return null;
	}
	
	@GetMapping("/hotels/{id}")
	public HotelDtoGetFullResponse getHotel(@PathVariable Long id) {
		/// TODO
		return null;
	}
	
	@PostMapping("/hotels")
	public HotelDtoResponse createHotel(@Valid @RequestBody HotelDtoRequest hotel) {
		return hotelService.createHotel(hotel);
	}
}
