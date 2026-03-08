package koroler.TThotels.params;

public record HotelParams (
	String name,
	String brand,
	String city, 
	String country, 
	String[] amenities
) {}
