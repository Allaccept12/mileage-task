package com.example.mileage.domain.review;


import com.example.mileage.domain.common.BaseEntity;
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
public class Review extends BaseEntity {

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
    @CollectionTable(name = "ATTACHED_PHOTOIDS", joinColumns = @JoinColumn(name = "review_id"))
    private List<String> attachedPhotoIds = new ArrayList<>();

    @Builder
    public Review(String id, String content, List<String> attachedPhotoIds, Place place, User user) {
        this.id = id;
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
        this.place = place;
        this.user = user;
        this.reviewType = ReviewType.NORMAL;
    }

    public void setReviewTypeForFirst() {
        this.reviewType = ReviewType.FIRST;
    }

    public boolean checkReviewOwner(String userId) {
        return this.user.getId().equals(userId);
    }

    public void setContentAndImageIds(String content, List<String> attachedPhotoIds) {
        this.content = content;
        this.attachedPhotoIds = attachedPhotoIds;
    }



}
