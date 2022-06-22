package com.example.mileage.service.point;

import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.point.PointRecord;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;
import com.example.mileage.repository.record.PointRecordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
class PointRecordServiceTest {

    @InjectMocks
    PointRecordServiceImpl pointRecordService;

    @Mock
    FixPointEarnService pointEarnService;

    @Mock
    PointRecordRepository pointRecordRepository;

    @Test
    @DisplayName("리뷰 작성시 포인트 이력 생성")
    void saveCreationPointRecord_test() {
        // given
        User userEntity = getUserEntity("user1");
        Place placeEntity = getPlaceEntity("place1", "jinju");
        Review reviewEntity = getReviewEntity(placeEntity, userEntity);
        Point point = Point.getInstance();
        point.plusOnePoint();
        point.plusOnePoint();
        point.plusBonusPoint();
        //mock
        given(pointEarnService.earnCreationReviewPoint(reviewEntity))
                .willReturn(point);;
        // when - then
        pointRecordService.saveCreationPointRecord(userEntity,reviewEntity,placeEntity);
    }

    @Test
    @DisplayName("리뷰 수정으로 인한 포인트 증감 이력 생성")
    void saveModifyingPointRecord_test() {
        // given
        ReviewEventDto modDto = new ReviewEventDto("REVIEW", "MOD",
                "review1", "컨텐츠 수정", new ArrayList<>(),
                "user1", "place1");
        User userEntity = getUserEntity("user1");
        Place placeEntity = getPlaceEntity("place1", "jinju");
        Review reviewEntity = getReviewEntity(placeEntity, userEntity);
        Point point = Point.getInstance();
        point.plusOnePoint();
        PointRecord pointRecord1 = getPointRecord(userEntity, placeEntity, point);
        PointRecord pointRecord2 = getPointRecord(userEntity, placeEntity, point);
        //mock
        given(pointRecordRepository.findByPlaceIdAndUserId(placeEntity.getId(),userEntity.getId()))
                .willReturn(Optional.of(List.of(pointRecord1,pointRecord2)));
        given(pointEarnService.earnModifyingReviewPoint(modDto,reviewEntity,true))
                .willReturn(point);
        // when - then
        pointRecordService.saveModifyingPointRecord(modDto,userEntity,reviewEntity,placeEntity);

    }

    @Test
    @DisplayName("리뷰 삭제로 인한 포인트 감소 이력생성")
    void saveDeletingPointRecord_test() {
        // given
        User userEntity = getUserEntity("user1");
        Place placeEntity = getPlaceEntity("place1", "jinju");
        Review reviewEntity = getReviewEntity(placeEntity, userEntity);
        Point point = Point.getInstance();
        point.plusOnePoint();
        PointRecord pointRecord1 = getPointRecord(userEntity, placeEntity, point);
        PointRecord pointRecord2 = getPointRecord(userEntity, placeEntity, point);
        //mock
        given(pointRecordRepository.findByPlaceIdAndUserId(placeEntity.getId(),userEntity.getId()))
                .willReturn(Optional.of(List.of(pointRecord1,pointRecord2)));
        given(pointEarnService.decreaseReviewPoint(reviewEntity,true))
                .willReturn(point);
        // when - then
        pointRecordService.saveDeletingPointRecord(userEntity,reviewEntity,placeEntity);
    }

    private PointRecord getPointRecord(User userEntity, Place placeEntity, Point point) {
        return PointRecord.builder()
                .user(userEntity)
                .place(placeEntity)
                .point(point)
                .build();
    }

    private User getUserEntity(String id) {
        return User.builder()
                .id(id)
                .nickName("사용자1")
                .build();
    }

    private Place getPlaceEntity(String id, String name) {
        return new Place(id,name);
    }

    private Review getReviewEntity(Place place, User user) {
        return Review.builder()
                .id("review1")
                .content("좋아요")
                .attachedPhotoIds(List.of("img1","img2","img3"))
                .place(place)
                .user(user)
                .build();
    }

}