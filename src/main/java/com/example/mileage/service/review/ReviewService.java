package com.example.mileage.service.review;

import com.example.mileage.domain.review.Review;
import com.example.mileage.dto.request.ReviewEventDto;

public interface ReviewService {

    void createReview(ReviewEventDto addDto);
    void modifyReview(ReviewEventDto modifyDto);
    void deleteReview(ReviewEventDto deleteDto);
    Review findReviewByReviewId(String reviewId);
    boolean existReviewByPlaceIdAndUserId(String placeId, String userId);
}
