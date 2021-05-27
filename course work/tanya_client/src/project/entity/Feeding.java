package project.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feeding {

    @SerializedName("feedingId")
    @Expose
    private Integer feedingId;

    @SerializedName("feedingName")
    @Expose
    private String feedingName;

    public Integer getFeedingId() {
        return feedingId;
    }

    public void setFeedingId(Integer feedingId) {
        this.feedingId = feedingId;
    }

    public String getFeedingName() {
        return feedingName;
    }

    public void setFeedingName(String feedingName) {
        this.feedingName = feedingName;
    }

    @Override
    public String toString() {
        return feedingName;
    }
}
