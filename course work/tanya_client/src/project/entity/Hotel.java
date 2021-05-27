package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {

    @SerializedName("hotelId")
    @Expose
    private Integer hotelId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("numberOfStars")
    @Expose
    private Integer numberOfStars;

    @SerializedName("priceForOneNight")
    @Expose
    private Double priceForOneNight;

    @SerializedName("accommodationType")
    @Expose
    private AccommodationType accommodationType;


    @SerializedName("feeding")
    @Expose
    private Feeding feeding;


    @SerializedName("city")
    @Expose
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

    @Override
    public String toString() {
        return name + ", " + city.getCityName();
    }
}
