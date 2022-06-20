package com.example.mileage.service.user;

import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.UserRequestDto;

public interface UserService {

    void registerUser(UserRequestDto registerDto);
    User findUserByUserId(String userId);
}
