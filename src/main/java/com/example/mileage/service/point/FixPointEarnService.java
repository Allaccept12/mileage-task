package com.example.mileage.service.point;


import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.review.ReviewType;
import com.example.mileage.dto.request.ReviewEventDto;
import org.springframework.stereotype.Service;

@Service
public class FixPointEarnService implements PointEarnService{

    public Point earnCreationReviewPoint(Review reviewEntity) {
        Point point = Point.getInstance();

        if (!reviewEntity.getContent().isBlank()) {
            point.plusOnePoint();
        }
        if (!reviewEntity.getAttachedPhotoIds().isEmpty()) {
            point.plusOnePoint();
        }
        if ((!reviewEntity.getContent().isBlank() || !reviewEntity.getAttachedPhotoIds().isEmpty())
                && reviewEntity.getReviewType().equals(ReviewType.FIRST)) {
            point.plusBonusPoint();
        }
        return point;
    }

    public Point earnModifyingReviewPoint(ReviewEventDto requestDto, Review reviewEntity, boolean isBonus) {
        Point point = Point.getInstance();

        if (checkImageDeletion(requestDto,reviewEntity)) {
            point.minusOnePoint();
        } else if (checkImageAddition(requestDto,reviewEntity)) {
            point.plusOnePoint();
        }

        if (checkContentDeletion(requestDto,reviewEntity)) {
            point.minusOnePoint();
        } else if (checkContentAddition(requestDto,reviewEntity)) {
            point.plusOnePoint();
        }

        if ((checkImageAddition(requestDto,reviewEntity) || checkContentAddition(requestDto,reviewEntity))
                && reviewEntity.getReviewType().equals(ReviewType.FIRST) && isBonus) {
            point.plusBonusPoint();

        } else if(reviewEntity.getAttachedPhotoIds().isEmpty() && reviewEntity.getContent().isBlank()){
            return point;

        } else if (requestDto.getContent().isBlank() && requestDto.getAttachedPhotoIds().isEmpty()
                && reviewEntity.getReviewType().equals(ReviewType.FIRST)) {
            point.minusBonusPoint();
        }

        return point;
    }

    public Point decreaseReviewPoint(Review review, boolean isBonus) {
        Point point = Point.getInstance();
        if (!review.getAttachedPhotoIds().isEmpty()) {
            point.minusOnePoint();
        }
        if (!review.getContent().isBlank()) {
            point.minusOnePoint();
        }
        if (review.getReviewType().equals(ReviewType.FIRST) && !isBonus) {
            point.minusBonusPoint();
        }
        return point;
    }

    private boolean checkImageAddition(ReviewEventDto requestDto, Review review) {
        return !requestDto.getAttachedPhotoIds().isEmpty() && review.getAttachedPhotoIds().isEmpty();
    }
    private boolean checkContentAddition(ReviewEventDto requestDto, Review review) {
        return !requestDto.getContent().isBlank() && review.getContent().isBlank();
    }
    private boolean checkImageDeletion(ReviewEventDto requestDto, Review review) {
        return requestDto.getAttachedPhotoIds().isEmpty() && !review.getAttachedPhotoIds().isEmpty();
    }
    private boolean checkContentDeletion(ReviewEventDto requestDto, Review review) {
        return requestDto.getContent().isEmpty() && !review.getContent().isEmpty();
    }
}
