package com.example.mileage.domain.reviewimage;


import com.example.mileage.domain.review.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "REVIEW_IMAGES")
public class ReviewImage {

    @Id
    @Column(name = "image_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    public ReviewImage(String id, Review review) {
        this.id = id;
        this.review = review;
    }
}
