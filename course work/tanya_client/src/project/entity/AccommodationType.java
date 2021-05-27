package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccommodationType {

    @SerializedName("accommodationTypeId")
    @Expose
    private Integer accommodationTypeId;

    @SerializedName("accommodationTypeName")
    @Expose
    private String accommodationTypeName;

    public Integer getAccommodationTypeId() {
        return accommodationTypeId;
    }

    public void setAccommodationTypeId(Integer accommodationTypeId) {
        this.accommodationTypeId = accommodationTypeId;
    }

    public String getAccommodationTypeName() {
        return accommodationTypeName;
    }

    public void setAccommodationTypeName(String accommodationTypeName) {
        this.accommodationTypeName = accommodationTypeName;
    }

    @Override
    public String toString() {
        return accommodationTypeName;
    }
}
