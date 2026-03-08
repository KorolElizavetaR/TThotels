package koroler.TThotels;

import org.springframework.data.jpa.domain.Specification;

import io.micrometer.common.util.StringUtils;
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
		this.name = params.name();
		this.brand = params.brand();
		this.city = params.city();
		this.country = params.country();
		this.amenities = params.amenities();
	}
	
	@Override
	public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		return cb.and(
				this.namePredicate(root, query, cb),
				this.brandPredicate(root, query, cb),
				this.cityPredicate(root, query, cb),
				this.countryPredicate(root, query, cb),
				this.amenitiesPredicate(root, query, cb)
				);
	}
	
	public Predicate namePredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (StringUtils.isBlank(name)) {
			return cb.conjunction();
		}
		return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
	}
	
	public Predicate brandPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (StringUtils.isBlank(brand)) {
			return cb.conjunction();
		}
		return cb.equal(cb.lower(root.get("brand").get("brandName")), brand.toLowerCase());
	}
	
	public Predicate cityPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (StringUtils.isBlank(city)) {
			return cb.conjunction();
		}
		return cb.equal(cb.lower(root.get("city").get("name")), city.toLowerCase());
	}
	
	public Predicate countryPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (StringUtils.isBlank(country)) {
			return cb.conjunction();
		}
		return cb.equal(cb.lower(root.get("city").get("country").get("name")), country.toLowerCase());
	}
	
	public Predicate amenitiesPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (amenities == null || amenities.length == 0) {
			return cb.conjunction();
		}
		Predicate[] predicates = new Predicate[amenities.length];
		for (int i = 0; i < amenities.length; i++) {
			predicates[i] = cb.equal(root.get("amenities").get("name"), amenities[i]);
		}
		return cb.and(predicates);
	}

}
