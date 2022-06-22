package com.example.mileage.repository.reivew;

import com.example.mileage.InitializeAfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;



class ReviewRepositoryTest extends InitializeAfterEach {

    @Test
    @DisplayName("리뷰 테이블에 플레이스 아이디가 있는지 - 최초 리뷰인지")
    void existsByPlaceId_test() {
        // given
        String placeId = "2e4baf1c-5acb-4efb-a1af-eddada31b001";
        // when
        boolean resultBoolean = reviewRepository.existsByPlaceId(placeId);
        // then
        assertTrue(resultBoolean);
    }

    @Test
    @DisplayName("리뷰테이블에 해당 장소아이디와 해당 유저아이디가 있는지 - 유저가 리뷰를 작성했었는지 ")
    void existsByPlaceIdAndUserId_test() {
        // given
        String userId = "3ede0ef2-92b7-4817-a5f3-0c575361f741";
        String placeId = "2e4baf1c-5acb-4efb-a1af-eddada31b001";
        // when
        boolean resultBoolean = reviewRepository.existsByPlaceIdAndUserId(placeId, userId);
        // then
        assertTrue(resultBoolean);
    }
}