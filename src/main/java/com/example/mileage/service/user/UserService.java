package com.example.mileage.service.user;

import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.UserRequestDto;
import com.example.mileage.dto.response.PointRecordResponseDto;

public interface UserService {

    void registerUser(UserRequestDto registerDto);

    PointRecordResponseDto getUserPointRecordAndTotalPoint(String userId);

    User findUserByUserId(String userId);

}
