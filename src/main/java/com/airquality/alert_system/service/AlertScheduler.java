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

    // We inject your brand new live API service here!
    @Autowired
    private AqiService aqiService;

    // Set to 10 seconds (10000) just for our live test right now.
    // We will change it back to 1 hour (3600000) after we see it work!
    @Scheduled(fixedRate = 360000)
    public void checkAqiAndSendAlerts() {
        System.out.println("\n⏰ [Background Task] Waking up to check air quality...");

        String cityToCheck = "Nagpur";

        // LIVE INTERNET CALL: We deleted the mock data and are asking the API!
        int liveAqi = aqiService.getRealTimeAqi(cityToCheck);

        // If the API fails or the token is wrong, it returns -1. Let's catch that
        // safely.
        if (liveAqi == -1) {
            System.out.println("⚠️ Could not fetch live data from WAQI API. Skipping this cycle.");
            return;
        }

        System.out.println("🌍 SUCCESS! Live AQI for " + cityToCheck + " is currently: " + liveAqi);

        // Search the database using the REAL live number
        List<User> usersAtRisk = userRepository.findByCityAndAqiThresholdLessThanEqual(cityToCheck, liveAqi);

        if (usersAtRisk.isEmpty()) {
            System.out.println("✅ Air quality is safe for everyone. No alerts needed.");
            return;
        }

        for (User user : usersAtRisk) {
            System.out.println("⚠️ Danger detected! Sending alert to: " + user.getName());
            emailService.sendAlertEmail("dhakatebhagyashree85@gmail.com", user.getCity(), liveAqi);
        }
    }
}