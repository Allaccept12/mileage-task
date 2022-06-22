package com.example.mileage.repository.record;

import com.example.mileage.domain.point.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    Optional<List<PointRecord>> findByPlaceIdAndUserId(String placeId, String userId);

    Optional<List<PointRecord>> findByUserIdOrderByCreatedDateDesc(String userId);
}
