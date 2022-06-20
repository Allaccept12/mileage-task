package com.example.mileage.domain.point;


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
public class PointRecord {

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
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public PointRecord(User user, Review review, Point point) {
        this.user = user;
        this.review = review;
        this.point = point;
    }

//    public void plusPoint() {
//        this.point.plusOnePoint();
//    }
//
//    public void minusPoint() {
//        this.point.minusOnePoint();
//    }
//    public void setPoint(String reviewContent, List<String> reviewImages) {
//        if (!reviewContent.isBlank()) {
//            this.point.plusOnePoint();
//        }
//        if (!reviewImages.isEmpty()) {
//            this.point.plusOnePoint();
//        }
//    }
}
