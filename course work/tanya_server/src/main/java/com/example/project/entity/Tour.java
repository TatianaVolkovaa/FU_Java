package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tourId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate beginDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate endDate;

    @Column(nullable = false)
    private Double priceForOnePerson;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "tour_type_id", nullable = false)
    private TourType tourType;

    @ManyToMany
    private Set<Flight> flights;

    @ManyToMany
    private Set<City> cities;

    @ManyToMany
    private Set<Hotel> hotels;

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPriceForOnePerson() {
        return priceForOnePerson;
    }

    public void setPriceForOnePerson(Double priceForOnePerson) {
        this.priceForOnePerson = priceForOnePerson;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }
}
