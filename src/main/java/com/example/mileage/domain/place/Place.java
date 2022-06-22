package com.example.mileage.domain.place;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLACES")
public class Place {

    @Id
    @Column(name = "place_id")
    private String id;

    @Column(nullable = false)
    private String placeName;

    public Place(String id, String placeName) {
        this.id = id;
        this.placeName = placeName;
    }

}
