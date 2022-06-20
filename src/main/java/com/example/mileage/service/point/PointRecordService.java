package com.example.mileage.service.point;

import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;

public interface PointRecordService {

    void saveCreationPointRecord(User user, Review reviewEntity);

    void saveModifyingPointRecord(ReviewEventDto modDto, User user, Review review);

    void saveDeletingPointRecord(ReviewEventDto deleteDto,User user, Review reviewEntity);

}
