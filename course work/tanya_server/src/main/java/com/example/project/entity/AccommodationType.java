package com.example.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "accommodation_types")
public class AccommodationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accommodationTypeId;

    @Column(nullable = false)
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
}
