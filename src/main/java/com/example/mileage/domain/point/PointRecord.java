package com.example.mileage.domain.point;


import com.example.mileage.domain.common.BaseEntity;
import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "POINT_RECORDS")
public class PointRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @Embedded
    private Point point;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Builder
    public PointRecord(User user, Place place, Point point) {
        this.user = user;
        this.place = place;
        this.point = point;
    }

}
