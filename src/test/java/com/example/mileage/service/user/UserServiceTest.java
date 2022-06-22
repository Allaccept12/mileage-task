package com.example.mileage.service.user;

import com.example.mileage.InitializeAfterEach;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.response.PointRecordResponseDto;
import com.example.mileage.dto.response.PointRecordResponseDto.PointDto;
import com.example.mileage.repository.user.UserRepository;
import com.example.mileage.service.point.PointRecordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest  {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PointRecordService pointRecordService;

    @Test
    @DisplayName("유저의 총 포인트 점수와 함께 포인트 증감 이력 목록 불러오기 테스트")
    void getUserPointRecordAndTotalPoint_test() {
        // given
        User userEntity = getUserEntity();
        PointRecordResponseDto responseData = PointRecordResponseDto.builder()
                .pointRecords(List.of(
                        new PointDto(2, 1, "jinju", userEntity.getId()),
                        new PointDto(2, 1, "busan", userEntity.getId())
                ))
                .build();

        //mocking
        given(userRepository.findById(userEntity.getId()))
                .willReturn(Optional.of(userEntity));
        given(pointRecordService.getUserPointRecordList(userEntity))
                .willReturn(responseData);
        // when
        PointRecordResponseDto result = userService.getUserPointRecordAndTotalPoint(userEntity.getId());
        // then
        assertEquals(6, result.getTotalPoint());
        assertEquals(2, result.getPointRecords().size());
        assertEquals("jinju", result.getPointRecords().get(0).getPlaceName());
        assertEquals("busan", result.getPointRecords().get(1).getPlaceName());

    }

    private User getUserEntity() {
        return User.builder()
                .id("user1")
                .nickName("사용자1")
                .build();
    }

}