package com.example.mileage.service.review;

import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.review.ReviewType;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;
import com.example.mileage.exception.ErrorCode;
import com.example.mileage.exception.exceptions.NotFoundReviewException;
import com.example.mileage.exception.exceptions.UserNotHaveReviewPermissionException;
import com.example.mileage.repository.place.PlaceRepository;
import com.example.mileage.repository.reivew.ReviewRepository;
import com.example.mileage.repository.user.UserRepository;
import com.example.mileage.service.place.PlaceService;
import com.example.mileage.service.place.PlaceServiceImpl;
import com.example.mileage.service.point.PointRecordService;
import com.example.mileage.service.point.PointRecordServiceImpl;
import com.example.mileage.service.user.UserService;
import com.example.mileage.service.user.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @InjectMocks
    ReviewServiceImpl reviewService;

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    UserServiceImpl userService;

    @Mock
    PlaceServiceImpl placeService;

    @Mock
    UserRepository userRepository;

    @Mock
    PlaceRepository placeRepository;

    @Mock
    PointRecordServiceImpl pointRecordService;

    @Test
    @DisplayName("리뷰 작성 서비스 로직 테스트 - 첫 리뷰")
    void createReview_test() {
        // given
        ReviewEventDto createDto = new ReviewEventDto("REVIEW", "ADD",
                "review1", "좋아요", List.of("img1", "img2", "img3"),
                "user1", "place1");

        User userEntity = getUserEntity("user1");
        Place placeEntity = getPlaceEntity("place1", "jinju");
        Review reviewEntity = getReviewEntity(placeEntity, userEntity);
        reviewEntity.setReviewTypeForFirst();
        //mock
        given(reviewRepository.existsByPlaceId(placeEntity.getId()))
                .willReturn(false);
        given(reviewRepository.save(any()))
                .willReturn(reviewEntity);
        given(reviewRepository.findById(reviewEntity.getId()))
                .willReturn(Optional.of(reviewEntity));
        given(placeService.findPlaceByPlaceId(placeEntity.getId()))
                .willReturn(placeEntity);
        given(userService.findUserByUserId(userEntity.getId()))
                .willReturn(userEntity);
        // when
        reviewService.createReview(createDto);
        // then
        Optional<Review> resultReview = reviewRepository.findById(reviewEntity.getId());

        assertEquals(resultReview.get().getId(), reviewEntity.getId());
        assertEquals(resultReview.get().getUser().getId(), userEntity.getId());
        assertEquals(resultReview.get().getContent(), createDto.getContent());
        assertEquals(resultReview.get().getReviewType(), ReviewType.FIRST);

    }

    @Test
    @DisplayName("리뷰 수정")
    void modifyReview_test() {
        // given
        ReviewEventDto modDto = new ReviewEventDto("REVIEW", "MOD",
                "review1", "컨텐츠 수정", new ArrayList<>(),
                "user1", "place1");
        User userEntity = getUserEntity("user1");
        Place placeEntity = getPlaceEntity("place1", "jinju");
        Review reviewEntity = getReviewEntity(placeEntity, userEntity);
        reviewEntity.setContentAndImageIds(modDto.getContent(), modDto.getAttachedPhotoIds());
        //mocking
        given(reviewRepository.findById(reviewEntity.getId()))
                .willReturn(Optional.of(reviewEntity));
        given(placeService.findPlaceByPlaceId(placeEntity.getId()))
                .willReturn(placeEntity);
        given(userService.findUserByUserId(userEntity.getId()))
                .willReturn(userEntity);
        // when
        reviewService.modifyReview(modDto);
        // then
        Optional<Review> resultReview = reviewRepository.findById(reviewEntity.getId());

        assertEquals(resultReview.get().getId(), reviewEntity.getId());
        assertEquals(resultReview.get().getUser().getId(), userEntity.getId());
        assertEquals(resultReview.get().getContent(), modDto.getContent());
        assertEquals(resultReview.get().getAttachedPhotoIds().size(),0);
    }
    @Test
    @DisplayName("리뷰 수정 - UserNotHaveReviewPermissionException throw")
    void modifyReview_permission_test() {
        // given
        ReviewEventDto modDto = new ReviewEventDto("REVIEW", "MOD",
                "review1", "컨텐츠 수정", new ArrayList<>(),
                "user1", "place1");
        User otherUser = getUserEntity("otherUser1");
        User userEntity = getUserEntity("user1");
        Place placeEntity = getPlaceEntity("place1", "jinju");
        Review reviewEntity = getReviewEntity(placeEntity, otherUser);

        reviewEntity.setContentAndImageIds(modDto.getContent(), modDto.getAttachedPhotoIds());
        //mocking
        given(reviewRepository.findById(reviewEntity.getId()))
                .willReturn(Optional.of(reviewEntity));
        given(placeService.findPlaceByPlaceId(placeEntity.getId()))
                .willReturn(placeEntity);
        given(userService.findUserByUserId(userEntity.getId()))
                .willReturn(userEntity);
        // when - then
        assertThatThrownBy(() -> {
            reviewService.modifyReview(modDto);
        }).isInstanceOf(UserNotHaveReviewPermissionException.class);
    }

    @Test
    @DisplayName("리뷰 삭제")
    void deleteReview_test() {
        // given
        ReviewEventDto deleteDto = new ReviewEventDto("REVIEW", "DELETE",
                "review1", "컨텐츠 수정", new ArrayList<>(),
                "user1", "place1");
        User userEntity = getUserEntity("user1");
        Place placeEntity = getPlaceEntity("place1", "jinju");
        Review reviewEntity = getReviewEntity(placeEntity, userEntity);
        // mock
        given(reviewRepository.findById(reviewEntity.getId()))
                .willReturn(Optional.of(reviewEntity));
        given(placeService.findPlaceByPlaceId(placeEntity.getId()))
                .willReturn(placeEntity);
        given(userService.findUserByUserId(userEntity.getId()))
                .willReturn(userEntity);
        // when - then
        reviewService.deleteReview(deleteDto);
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

    private Review  getReviewEntity(Place place, User user) {
        return Review.builder()
                .id("review1")
                .content("좋아요")
                .attachedPhotoIds(List.of("img1","img2","img3"))
                .place(place)
                .user(user)
                .build();
    }



}