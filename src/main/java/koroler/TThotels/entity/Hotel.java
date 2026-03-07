package koroler.TThotels.entity;

import java.time.LocalTime;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hotel")
@Getter
@Setter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "address")
    private Address address;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactInfo contactInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "check_in")
    private LocalTime checkIn;

    @Column(name = "check_out")
    private LocalTime checkOut;

    @ManyToMany
    @JoinTable(
        name = "amenities_hotel",
        joinColumns = @JoinColumn(name = "hotel_id"),
        inverseJoinColumns = @JoinColumn(name = "amenities_id")
    )
    private Set<Amenity> amenities;
    
    
    @Data
    public static class Address {
    	private Integer houseNumber;
    	private String street;
    	private String postcode;
    }
}
