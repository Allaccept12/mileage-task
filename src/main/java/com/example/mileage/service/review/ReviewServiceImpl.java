package com.example.mileage.service.review;

import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;
import com.example.mileage.exception.exceptions.UserNotHaveReviewPermissionException;
import com.example.mileage.exception.exceptions.NotFoundReviewException;
import com.example.mileage.exception.ErrorCode;
import com.example.mileage.repository.reivew.ReviewRepository;
import com.example.mileage.service.place.PlaceService;
import com.example.mileage.service.point.PointRecordService;
import com.example.mileage.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final PlaceService placeService;
    private final PointRecordService pointRecordService;

    @Transactional
    public void createReview(ReviewEventDto addDto) {
        User userEntity = userService.findUserByUserId(addDto.getUserId());
        Place placeEntity = placeService.findPlaceByPlaceId(addDto.getPlaceId());
        Review review = getReview(addDto, userEntity, placeEntity);
        // 해당 장소에 등록된 리뷰가 있는지
        if (!reviewRepository.existsByPlaceId(placeEntity.getId())) {
            review.setReviewTypeForFirst();
        }
        reviewRepository.save(review);
        pointRecordService.saveCreationPointRecord(userEntity, review, placeEntity);
    }

    @Transactional
    public void modifyReview(ReviewEventDto modDto) {
        Review reviewEntity = findReviewByReviewId(modDto.getReviewId());
        Place placeEntity = placeService.findPlaceByPlaceId(modDto.getPlaceId());
        User userEntity = userService.findUserByUserId(modDto.getUserId());
        verifyReviewOwner(reviewEntity, userEntity);
        pointRecordService.saveModifyingPointRecord(modDto,userEntity,reviewEntity,placeEntity);
        reviewEntity.setContentAndImageIds(modDto.getContent(), modDto.getAttachedPhotoIds());
    }

    @Transactional
    public void deleteReview(ReviewEventDto deleteDto) {
        Review reviewEntity = findReviewByReviewId(deleteDto.getReviewId());
        Place placeEntity = placeService.findPlaceByPlaceId(deleteDto.getPlaceId());
        User userEntity = userService.findUserByUserId(deleteDto.getUserId());
        verifyReviewOwner(reviewEntity, userEntity);
        pointRecordService.saveDeletingPointRecord(userEntity,reviewEntity,placeEntity);
        reviewRepository.delete(reviewEntity);
    }

    @Transactional(readOnly = true)
    public boolean existReviewByPlaceIdAndUserId(String placeId,String userId) {
        return reviewRepository.existsByPlaceIdAndUserId(placeId, userId);
    }

    @Override
    public Review findReviewByReviewId(String reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundReviewException(ErrorCode.NOT_FOUND_REVIEW));

    }

    private void verifyReviewOwner(Review reviewEntity, User userEntity) {
        if (!reviewEntity.checkReviewOwner(userEntity.getId())) {
            throw new UserNotHaveReviewPermissionException(ErrorCode.USER_NOT_HAVE_REVIEW_PERMISSION);
        }
    }

    private Review getReview(ReviewEventDto addDto, User userEntity, Place placeEntity) {
        return Review.builder()
                .id(addDto.getReviewId())
                .content(addDto.getContent())
                .attachedPhotoIds(addDto.getAttachedPhotoIds())
                .place(placeEntity)
                .user(userEntity)
                .build();

    }
}
