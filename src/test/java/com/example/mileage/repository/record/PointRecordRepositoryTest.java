package com.example.mileage.repository.record;

import com.example.mileage.InitializeAfterEach;
import com.example.mileage.domain.point.PointRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PointRecordRepositoryTest extends InitializeAfterEach {


    @Test
    @DisplayName("특정 장소 포인트 이력 리스트 뽑기")
    void findByUserIdAndPlaceId_test() {
        // given
        String userId = "3ede0ef2-92b7-4817-a5f3-0c575361f741";
        String placeId = "2e4baf1c-5acb-4efb-a1af-eddada31b001";
        // when
        Optional<List<PointRecord>> resultList = pointRecordRepository.findByUserIdAndPlaceId(userId, placeId);
        // then
        assertTrue(resultList.isPresent());
        assertEquals(1,resultList.get().size());
        assertEquals(1,resultList.get().get(0).getPoint().getBonusPoint());
        assertEquals(2,resultList.get().get(0).getPoint().getContentPoint());
    }

    @Test
    @DisplayName("유저의 전체 장소에 대한 포인트 이력 리스트 뽑기")
    void findByUserIdOrderByCreatedDateDesc_test() {
        // given
        String userId = "3ede0ef2-92b7-4817-a5f3-0c575361f741";
        // when
        Optional<List<PointRecord>> resultList =
                pointRecordRepository.findByUserIdOrderByCreatedDateDesc(userId);
        // then
        assertTrue(resultList.isPresent());
        assertEquals(2,resultList.get().size());
        assertEquals(1,resultList.get().get(0).getPoint().getBonusPoint());
        assertEquals(1,resultList.get().get(0).getPoint().getContentPoint());

        assertEquals(1,resultList.get().get(1).getPoint().getBonusPoint());
        assertEquals(2,resultList.get().get(1).getPoint().getContentPoint());
    }

}