package koroler.TThotels.controller;

import java.util.HashMap;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import koroler.TThotels.dto.hotel.HotelDtoGetFullResponse;
import koroler.TThotels.dto.hotel.HotelDtoRequest;
import koroler.TThotels.dto.hotel.HotelDtoResponse;
import koroler.TThotels.params.HotelParams;
import koroler.TThotels.service.HotelService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/property-view")
public class HotelController {
	
	private final HotelService hotelService;
	
	@GetMapping("/hotels")
	@Operation(description = "gолучение списка всех отелей с их краткой информацией")
	public List<HotelDtoResponse> getHotels() {
		return hotelService.getHotels();
	}
	
	@GetMapping("/hotels/{id}")
	@Operation(description = "получение расширенной информации по конктретному отелю")
	public HotelDtoGetFullResponse getHotel(@PathVariable Long id) {
		return hotelService.getHotelById(id);
	}
	
	@GetMapping("/search")
	@Operation(
			summary = "получение списка отелей по параметрам",
			description = "поиск получение списка всех отелей с их краткой информацией по следующим параметрам: name, brand, city, country, amenities. ")
	public List<HotelDtoResponse> getHotelByParams(@ParameterObject HotelParams params) {
		return hotelService.getHotelByParams(params);
	}
	
	@PostMapping("/hotels")
	@Operation(summary = "создание нового отеля")
	public HotelDtoResponse createHotel(@Valid @RequestBody HotelDtoRequest hotel) {
		return hotelService.createHotel(hotel);
	}
	
	@PostMapping("/hotels/{id}/amenities")
	@Operation(summary = "добавление списка amenities к отелю")
	public void addAmenitiesToHotel(@RequestBody String amenities) {
		//TODO: impl
	}
	
	@GetMapping("/histogram/{param}")
	@Operation(
			summary = "получение кол-ва отелей сгруппированных по каждому значению",
			description = "получение колличества отелей сгруппированных по каждому значению указанного параметра. Параметр: brand, city, country, amenities.")
	public HashMap<String, Integer> getHotelsAmountGrouped(@PathVariable String groupingFactor) {
		//TODO: impl
		return null;
	}
}
