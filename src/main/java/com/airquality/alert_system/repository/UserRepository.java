package com.airquality.alert_system.repository;

import com.airquality.alert_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Add this import!
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // Your existing method
    List<User> findByCityAndAqiThresholdLessThanEqual(String city, int aqiThreshold);

    // NEW METHOD: Get a list of unique cities where users currently live
    @Query("SELECT DISTINCT u.city FROM User u")
    List<String> findDistinctCities();
}