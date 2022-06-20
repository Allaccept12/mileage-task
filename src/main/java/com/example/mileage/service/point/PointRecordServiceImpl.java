package com.example.mileage.service.point;

import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.point.PointRecord;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;
import com.example.mileage.repository.record.PointRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointRecordServiceImpl implements PointRecordService{

    private final PointRecordRepository pointRecordRepository;
    private final PointRecordUtil pointRecordUtil;

    @Override
    public void saveCreationPointRecord(User userEntity, Review reviewEntity) {
        Point point = pointRecordUtil.earnCreationReviewPoint(reviewEntity);
        //저장될 포인트가 빵점일 경우는 이력을 저장하지 않는다.
        if (point.verifyPointWhenZero()) {
            PointRecord pointRecord = getPointRecord(userEntity, reviewEntity, point);
            pointRecordRepository.save(pointRecord);
        }
    }

    @Override
    public void saveModifyingPointRecord(ReviewEventDto modDto, User userEntity, Review reviewEntity) {
        Point point = pointRecordUtil.verifyModifyingReviewPoint(modDto, reviewEntity);
        if (point.verifyPointWhenZero()) {
            PointRecord pointRecord = getPointRecord(userEntity, reviewEntity, point);
            pointRecordRepository.save(pointRecord);
        }
    }

    @Override
    public void saveDeletingPointRecord(ReviewEventDto deleteDto,User userEntity, Review review) {
        Point point = pointRecordUtil.decreaseReviewPoint(deleteDto, review);
        if (point.verifyPointWhenZero()) {
            PointRecord pointRecord = getPointRecord(userEntity, review, point);
            pointRecordRepository.save(pointRecord);
        }
    }

    private PointRecord getPointRecord(User userEntity, Review reviewEntity, Point point) {
        return PointRecord.builder()
                .user(userEntity)
                .review(reviewEntity)
                .point(point)
                .build();
    }
}
