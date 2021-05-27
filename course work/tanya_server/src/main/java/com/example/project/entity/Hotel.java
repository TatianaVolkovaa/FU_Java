package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer hotelId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer numberOfStars;

    @Column(nullable = false)
    private Double priceForOneNight;

    @ManyToOne
    @JoinColumn(name = "accommodation_type_id", nullable = false)
    private AccommodationType accommodationType;

    @ManyToOne
    @JoinColumn(name = "feeding_id", nullable = false)
    private Feeding feeding;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public Double getPriceForOneNight() {
        return priceForOneNight;
    }

    public void setPriceForOneNight(Double priceForOneNight) {
        this.priceForOneNight = priceForOneNight;
    }

    public AccommodationType getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(AccommodationType accommodationType) {
        this.accommodationType = accommodationType;
    }

    public Feeding getFeeding() {
        return feeding;
    }

    public void setFeeding(Feeding feeding) {
        this.feeding = feeding;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
