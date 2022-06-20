package com.example.mileage.service.point;


import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.review.ReviewType;
import com.example.mileage.dto.request.ReviewEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointRecordUtil {

    public Point earnCreationReviewPoint(Review reviewEntity) {
        Point point = Point.getInstance();

        if (!reviewEntity.getContent().isBlank()) {
            //코멘트 있을떄
            point.plusOnePoint();
        }
        if (!reviewEntity.getImageIds().isEmpty()) {
            //사진이 있을떄
            point.plusOnePoint();
        }
        //최초 리뷰인지 확인(보너스 점수 1점) - 이미지랑 리부메시지 없으면 보너스 점수 x
        if (!reviewEntity.getContent().isBlank()
                && !reviewEntity.getImageIds().isEmpty()
                && reviewEntity.getReviewType().equals(ReviewType.FIRST)) {
            //최초 리뷰 라면
            point.plusOnePoint();
        }
        return point;
    }

    public Point verifyModifyingReviewPoint(ReviewEventDto requestDto, Review reviewEntity) {
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
                && reviewEntity.getReviewType().equals(ReviewType.FIRST)) {
            //고쳤는데 첫번째 리뷰이면서 리뷰에 코멘트나 사진이 추가된경우
            point.plusOnePoint();
        } else if (checkContentDeletion(requestDto, reviewEntity) && checkImageDeletion(requestDto, reviewEntity)
                && reviewEntity.getReviewType().equals(ReviewType.FIRST)) {
            //고쳤는데 첫번째 리뷰이면서 리뷰에 코멘트나 사진이 다 사라진 경우
            point.minusOnePoint();
        }
        return point;
    }

    public Point decreaseReviewPoint(ReviewEventDto deleteDto, Review review) {
        Point point = Point.getInstance();
        if (checkImageDeletion(deleteDto,review)) {
            point.minusOnePoint();
        }
        if (checkContentDeletion(deleteDto,review)) {
            point.minusOnePoint();
        }
        if (checkContentDeletion(deleteDto, review) && checkImageDeletion(deleteDto, review)
                && review.getReviewType().equals(ReviewType.FIRST)) {
            //삭제하는데 첫번째 리뷰이면서 리뷰에 코멘트나 사진이 없다면
            point.minusOnePoint();
        }
        return point;
    }

    public boolean checkImageAddition(ReviewEventDto requestDto, Review review) {
        return !requestDto.getAttachedPhotoIds().isEmpty() && review.getImageIds().isEmpty();
    }
    public boolean checkContentAddition(ReviewEventDto requestDto, Review review) {
        return !requestDto.getContent().isBlank() && review.getContent().isBlank();
    }
    public boolean checkImageDeletion(ReviewEventDto requestDto, Review review) {
        return requestDto.getAttachedPhotoIds().isEmpty() && !review.getImageIds().isEmpty();
    }
    public boolean checkContentDeletion(ReviewEventDto requestDto, Review review) {
        return requestDto.getContent().isEmpty() && !review.getContent().isEmpty();
    }
}
