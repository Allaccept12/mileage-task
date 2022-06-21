package com.example.mileage.service.point;

import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;

public interface PointEarnService {

    Point earnCreationReviewPoint(Review reviewEntity);

    Point earnModifyingReviewPoint(ReviewEventDto requestDto, Review reviewEntity, boolean isBonus);

    Point decreaseReviewPoint(Review review, boolean isBonus);

}
