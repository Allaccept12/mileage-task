package com.example.mileage.service.point;

import com.example.mileage.domain.place.Place;
import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.point.PointRecord;
import com.example.mileage.domain.review.Review;
import com.example.mileage.domain.user.User;
import com.example.mileage.dto.request.ReviewEventDto;
import com.example.mileage.dto.response.PointRecordResponseDto;
import com.example.mileage.dto.response.PointRecordResponseDto.PointDto;
import com.example.mileage.exception.exceptions.NotFoundReviewRecordException;
import com.example.mileage.exception.ErrorCode;
import com.example.mileage.repository.record.PointRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointRecordServiceImpl implements PointRecordService{

    private final PointRecordRepository pointRecordRepository;
    private final PointEarnService fixPointEarnService;

    @Override
    public void saveCreationPointRecord(User userEntity, Review reviewEntity, Place placeEntity) {
        Point contentPoint = fixPointEarnService.earnCreationReviewPoint(reviewEntity);
        //저장될 포인트가 빵점일 경우는 이력을 저장하지 않는다.
        if (contentPoint.verifyPointWhenZero()) {
            PointRecord pointRecord = getPointRecord(userEntity, placeEntity, contentPoint);
            pointRecordRepository.save(pointRecord);
        }
    }

    @Override
    public void saveModifyingPointRecord(ReviewEventDto modDto, User userEntity,
                                         Review reviewEntity, Place placeEntity) {
        final int totalBonusPoint = getTotalBonusPoint(userEntity, placeEntity);
        Point point = fixPointEarnService
                .earnModifyingReviewPoint(modDto, reviewEntity,totalBonusPoint == 0);
        if (point.verifyPointWhenZero()) {
            PointRecord pointRecord = getPointRecord(userEntity, placeEntity, point);
            pointRecordRepository.save(pointRecord);
        }
    }

    @Override
    public void saveDeletingPointRecord(User userEntity, Review reviewEntity, Place placeEntity) {
        final int totalBonusPoint = getTotalBonusPoint(userEntity, placeEntity);
        Point point = fixPointEarnService
                .decreaseReviewPoint(reviewEntity, totalBonusPoint == 0);
        if (point.verifyPointWhenZero()) {
            PointRecord pointRecord = getPointRecord(userEntity, placeEntity, point);
            pointRecordRepository.save(pointRecord);
        }
    }

    @Override
    public PointRecordResponseDto getUserPointRecordList(User user) {
        List<PointRecord> pointRecords = getAllPointRecords(user);
        List<PointDto> pointDtoList = pointRecords.stream()
                .map(PointDto::of)
                .collect(Collectors.toList());

        return PointRecordResponseDto.builder()
                .pointRecords(pointDtoList)
                .build();
    }

    private int getTotalBonusPoint(User userEntity, Place placeEntity) {
        List<PointRecord> recentRecordList = pointRecordRepository
                .findByPlaceIdAndUserId(userEntity.getId(), placeEntity.getId())
                .orElseThrow(() -> new NotFoundReviewRecordException(ErrorCode.NOT_FOUND_REVIEW_RECORD));
        return recentRecordList.stream()
                .mapToInt(data -> data.getPoint().getBonusPoint())
                .sum();
    }

    private List<PointRecord> getAllPointRecords(User user) {
        return pointRecordRepository.findByUserIdOrderByCreatedDateDesc(user.getId())
                .orElseThrow(() -> new NotFoundReviewRecordException(ErrorCode.NOT_FOUND_REVIEW_RECORD));
    }

    private PointRecord getPointRecord(User userEntity, Place placeEntity, Point point) {
        return PointRecord.builder()
                .user(userEntity)
                .place(placeEntity)
                .point(point)
                .build();
    }
}
