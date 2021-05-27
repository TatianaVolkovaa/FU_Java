package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("flightId")
    @Expose
    private Integer flightId;

    @SerializedName("departureTime")
    @Expose
    private String departureTime;

    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;

    @SerializedName("price")
    @Expose
    private Double price;

    @SerializedName("departureCity")
    @Expose
    private City departureCity;

    @SerializedName("arrivalCity")
    @Expose
    private City arrivalCity;

    @SerializedName("company")
    @Expose
    private String company;

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
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
