package com.example.mileage.controller;


import com.example.mileage.dto.request.UserRequestDto;
import com.example.mileage.dto.response.PointRecordResponseDto;
import com.example.mileage.service.point.PointRecordService;
import com.example.mileage.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/user/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.OK).body("성공");
    }

    /**
     * 유저 총 획득 포인트와 포인트 증감 이력 조회
     *
     */
    @GetMapping("/api/user/point-record/{id}")
    public ResponseEntity<PointRecordResponseDto> getUserPointRecordAndTotalPoint(
            @PathVariable("id") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserPointRecordAndTotalPoint(userId));
    }
}
