package com.example.mileage.domain.place;


import com.example.mileage.domain.review.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PLACES")
public class Place {

    @Id
    @Column(name = "place_id")
    private String id;

    public Place(String id) {
        this.id = id;
    }

}
