package koroler.TThotels;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import koroler.TThotels.entity.Hotel;
import koroler.TThotels.params.HotelParams;

public class HotelSpecification implements Specification<Hotel> {

	private String name;
	private String brand;
	private String city;
	private String country; 
	private String[] amenities;
	
	public HotelSpecification(HotelParams params) {
		
	}
	
	@Override
	public @Nullable Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

}
