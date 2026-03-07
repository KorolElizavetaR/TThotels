package koroler.TThotels.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contact_info")
@Getter
@Setter
public class ContactInfo {

    @Id
    @Column(name = "hotel_id")
    private Long hotelId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @OneToOne
    @MapsId 
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

}
