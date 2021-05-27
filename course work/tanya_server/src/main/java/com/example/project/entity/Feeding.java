package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "feeding")
public class Feeding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedingId;

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
}
