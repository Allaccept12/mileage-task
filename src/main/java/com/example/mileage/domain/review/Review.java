package com.example.mileage.domain.review;


import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Table(name = "REVIEWS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @Column(name = "review_id")
    private String id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    private ReviewType reviewType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(name = "IMAGES", joinColumns = @JoinColumn(name = "review_id"))
    private List<String> imageIds = new ArrayList<>();

    @Builder
    public Review(String id, String content, List<String> imageIds, Place place, User user) {
        this.id = id;
        this.content = content;
        this.imageIds = imageIds;
        this.place = place;
        this.user = user;
    }

    @PrePersist
    private void initReviewType() {
        this.reviewType = ReviewType.NORMAL;
    }

    public void setReviewTypeForFirst() {
        this.reviewType = ReviewType.FIRST;
    }

    public void setContentAndImageIds(String content, List<String> imageIds) {
        this.content = content;
        this.imageIds = imageIds;
    }



}
