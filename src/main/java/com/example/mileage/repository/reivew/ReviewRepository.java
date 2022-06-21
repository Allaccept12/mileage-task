package com.example.mileage.repository.reivew;

import com.example.mileage.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, String> {

    boolean existsByPlaceId(String placeId);
    boolean existsByPlaceIdAndUserId(String placeId, String userId);
}
