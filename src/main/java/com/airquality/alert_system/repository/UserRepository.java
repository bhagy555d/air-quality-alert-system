package com.airquality.alert_system.repository;

import com.airquality.alert_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Boot magically turns this method name into a SQL query!
    // It will find a user by their email address.
    Optional<User> findByEmail(String email);

    // This query finds all users in a specific city who have a threshold LOWER than
    // the current pollution level.
    // We will use this exactly for our hourly background task!
    List<User> findByCityAndAqiThresholdLessThanEqual(String city, int currentAqi);
}