package com.example.mileage.service.point;

import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;
import com.example.mileage.dto.response.PointRecordResponseDto;

import java.util.List;

public interface PointRecordService {

    void saveCreationPointRecord(User user, Review reviewEntity, Place placeEntity);

    void saveModifyingPointRecord(ReviewEventDto modDto, User user, Review review, Place placeEntity);

    void saveDeletingPointRecord(User user, Review reviewEntity, Place placeEntity);

    PointRecordResponseDto getUserPointRecordList(User user);



}
