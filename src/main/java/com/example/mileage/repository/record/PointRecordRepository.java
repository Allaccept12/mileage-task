package com.example.mileage.repository.record;

import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.point.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    @Query("select p.point from PointRecord p where p.user.id =:userId and p.review.place.id =:placeId")
    List<Point> findPointByUserIdAndPlaceId(@Param("userId") String userId ,@Param("placeId") String placeId);
}
