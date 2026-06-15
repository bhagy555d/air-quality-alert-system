package com.airquality.alert_system.service;

import com.airquality.alert_system.entity.User;
import com.airquality.alert_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertScheduler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // we will make it run every 60 minutes
    @Scheduled(fixedRate = 360000)
    public void checkAqiAndSendAlerts() {
        System.out.println(" [Background Task] Waking up to check air quality...");

        // NOTE: Since we haven't connected the live WAQI API yet,
        // we are using a "mock" (fake) AQI value of 150 for Nagpur to test the logic.
        String cityToCheck = "Nagpur";
        int currentMockAqi = 150;

        // 1. Search the database for anyone in Nagpur whose threshold is 150 or lower
        List<User> usersAtRisk = userRepository.findByCityAndAqiThresholdLessThanEqual(cityToCheck, currentMockAqi);

        // 2. If the list is empty, print a safe message
        if (usersAtRisk.isEmpty()) {
            System.out.println("✅ Air quality is safe. No alerts needed for " + cityToCheck);
            return;
        }

        // 3. If people are at risk, loop through the list and email them!
        for (User user : usersAtRisk) {
            System.out.println("⚠️ Danger detected! Sending alert to: " + user.getName());
            emailService.sendAlertEmail("dhakatebhagyashree85@gmail.com", user.getCity(), currentMockAqi);
        }
    }
}
