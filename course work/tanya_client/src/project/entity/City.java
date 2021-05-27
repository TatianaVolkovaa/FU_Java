package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class City {


    @SerializedName("cityId")
    @Expose
    private Integer cityId;

    @SerializedName("cityName")
    @Expose
    private String cityName;

    @SerializedName("country")
    @Expose
    private Country country;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return cityName;
    }
}
