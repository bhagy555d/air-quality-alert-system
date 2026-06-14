package com.airquality.alert_system.repository;

import com.airquality.alert_system.entity.AqiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AqiLogRepository extends JpaRepository<AqiLog, Long> {

    // Finds all pollution records for a specific city, ordered from newest to
    // oldest
    List<AqiLog> findByCityOrderByTimestampDesc(String city);
}