package com.example.mileage.service.user;


import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.UserRequestDto;
import com.example.mileage.dto.response.PointRecordResponseDto;
import com.example.mileage.exception.exceptions.NotFoundUserException;
import com.example.mileage.exception.ErrorCode;
import com.example.mileage.repository.user.UserRepository;
import com.example.mileage.service.point.PointRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PointRecordService pointRecordService;

    @Transactional
    public void registerUser(UserRequestDto registerDto) {
        User userEntity = User.builder()
                .id(registerDto.getUserId())
                .nickName(registerDto.getNickName())
                .build();
        userRepository.save(userEntity);
    }

    @Transactional
    public PointRecordResponseDto getUserPointRecordAndTotalPoint(String userId) {
        User userEntity = findUserByUserId(userId);
        return pointRecordService.getUserPointRecordList(userEntity);
    }

    @Override
    public User findUserByUserId(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException(ErrorCode.NOT_FOUND_USER));
    }
}
