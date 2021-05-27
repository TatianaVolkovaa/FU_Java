package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flightId;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime departureTime;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "departure_city_id", nullable = false)
    private City departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id", nullable = false)
    private City arrivalCity;

    @Column(nullable = false)
    private String company;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
