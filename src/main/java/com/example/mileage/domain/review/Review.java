package com.example.mileage.domain.review;


import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.reviewimage.ReviewImage;
import com.example.mileage.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "REVIEWS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(nullable = false,length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "review",orphanRemoval = true,cascade = CascadeType.REMOVE)
    private List<ReviewImage> reviewImages = new ArrayList<>();

    @Builder
    public Review(String reviewId, String content, Place place, User user) {
        this.reviewId = reviewId;
        this.content = content;
        this.place = place;
        this.user = user;
    }

    @PrePersist
    private void initContent() {
        this.content = this.content != null ? this.content : "";
    }


}
