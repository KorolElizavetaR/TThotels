package koroler.TThotels.params;

import org.jspecify.annotations.Nullable;

public record HotelParams (
	@Nullable String name,
	@Nullable String brand,
	@Nullable String city, 
	@Nullable String country, 
	@Nullable String[] amenities
) {}
