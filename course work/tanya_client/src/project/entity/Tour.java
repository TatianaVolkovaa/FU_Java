package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class Tour {

    @SerializedName("tourId")
    @Expose
    private Integer tourId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("beginDate")
    @Expose
    private String beginDate;

    @SerializedName("endDate")
    @Expose
    private String endDate;

    @SerializedName("priceForOnePerson")
    @Expose
    private Double priceForOnePerson;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("tourType")
    @Expose
    private TourType tourType;

    @SerializedName("flights")
    @Expose
    private Set<Flight> flights;

    @SerializedName("cities")
    @Expose

    private Set<City> cities;

    @SerializedName("hotels")
    @Expose
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

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
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

    @Override
    public String toString() {
        return name;
    }
}
