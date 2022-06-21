package com.example.mileage.controller;


import com.example.mileage.dto.request.ReviewEventDto;
import com.example.mileage.exception.AlreadyWroteReviewException;
import com.example.mileage.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/api/events")
    public ResponseEntity<String> createReview(@Valid @RequestBody ReviewEventDto reviewDto) {

        if (reviewService.existReviewByPlaceIdAndUserId(reviewDto.getUserId(), reviewDto.getPlaceId())) {
            throw new AlreadyWroteReviewException("이미 평가한 상품");
        }
        reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }

    @PatchMapping("/api/events")
    public ResponseEntity<String> modifyReview(@Valid @RequestBody ReviewEventDto reviewDto) {
        reviewService.modifyReview(reviewDto);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }

    @DeleteMapping("/api/events")
    public ResponseEntity<String> deleteReview(@Valid @RequestBody ReviewEventDto reviewDto) {
        reviewService.deleteReview(reviewDto);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }
}
