package com.example.mileage.repository.record;

import com.example.mileage.domain.point.Point;
import com.example.mileage.domain.point.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    List<PointRecord> findByUserIdAndPlaceId(String userId, String placeId);

    Optional<List<PointRecord>> findByUserIdOrderByCreatedDateDesc(String userId);
}
