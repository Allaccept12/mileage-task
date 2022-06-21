package com.example.mileage.dto.response;


import com.example.mileage.domain.point.PointRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PointRecordResponseDto {

    private int totalPoint;
    private List<PointDto> pointRecords;

    @Builder
    public PointRecordResponseDto(List<PointDto> pointRecords) {
        this.totalPoint = getTotalPoint(pointRecords);
        this.pointRecords = pointRecords;
    }

    private int getTotalPoint(List<PointDto> pointRecords) {
        return pointRecords.stream()
                .mapToInt(data -> data.getPoint() + data.getBonusPoint())
                .sum();
    }

    @NoArgsConstructor
    @Data
    public static class PointDto {

        private int point;
        private int bonusPoint;
        private String placeName;
        private String userId;


        @Builder
        public PointDto(int point,int bonusPoint, String placeName, String userId) {
            this.point = point;
            this.bonusPoint = bonusPoint;
            this.placeName = placeName;
            this.userId = userId;
        }

        public static PointDto of(PointRecord pointRecord) {
            return PointDto.builder()
                    .point(pointRecord.getPoint().getContentPoint())
                    .bonusPoint(pointRecord.getPoint().getBonusPoint())
                    .placeName(pointRecord.getPlace().getPlaceName())
                    .userId(pointRecord.getUser().getId())
                    .build();
        }
    }

}
