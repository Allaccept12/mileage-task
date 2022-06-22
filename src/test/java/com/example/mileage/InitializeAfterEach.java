package com.example.mileage;

import com.example.mileage.repository.place.PlaceRepository;
import com.example.mileage.repository.record.PointRecordRepository;
import com.example.mileage.repository.reivew.ReviewRepository;
import com.example.mileage.repository.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@ActiveProfiles("test")
@Sql("classpath:data.sql")
public class InitializeAfterEach {

    @Autowired
    public ReviewRepository reviewRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public PlaceRepository placeRepository;
    @Autowired
    public PointRecordRepository pointRecordRepository;

    @AfterEach
    void afterEach() {
        pointRecordRepository.deleteAll();
        reviewRepository.deleteAll();
        placeRepository.deleteAll();
        userRepository.deleteAll();
    }

}
