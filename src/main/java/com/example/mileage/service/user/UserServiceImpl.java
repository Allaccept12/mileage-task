package com.example.mileage.service.user;


import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.UserRequestDto;
import com.example.mileage.exception.NotFoundUserException;
import com.example.mileage.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Transactional
    public void registerUser(UserRequestDto registerDto) {
        User userEntity = User.builder()
                .id(registerDto.getUserId())
                .nickName(registerDto.getNickName())
                .build();
        userRepository.save(userEntity);
    }

    @Override
    public User findUserByUserId(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
    }
}
