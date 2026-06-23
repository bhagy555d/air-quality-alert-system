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

    @Autowired
    private AqiService aqiService;

    @Autowired
    private MlPredictionService mlPredictionService;

    // Set to 60 seconds for sending alerts
    @Scheduled(fixedRate = 60000)
    public void checkAqiAndPredict() {
        System.out.println("\n⏰ [Background Task] Waking up to scan global cities...");

        // 1. Ask the database for a list of all unique cities
        List<String> activeCities = userRepository.findDistinctCities();

        if (activeCities.isEmpty()) {
            System.out.println("📭 No users registered yet. Going back to sleep.");
            return;
        }

        // 2. Loop through every single city dynamically
        for (String cityToCheck : activeCities) {
            System.out.println("\n----------------------------------------");
            System.out.println("🌍 Scanning city: " + cityToCheck);

            // Fetch live internet data for THIS specific city
            int liveAqi = aqiService.getRealTimeAqi(cityToCheck);
            if (liveAqi == -1) {
                System.out.println("❌ Failed to fetch data for " + cityToCheck + ". Skipping...");
                continue;
            }
            System.out.println("📊 Live AQI is currently: " + liveAqi);

            // Ask Python for tomorrow's prediction
            int predictedAqi = mlPredictionService.getPredictedTomorrowAqi(liveAqi);
            if (predictedAqi != -1) {
                System.out.println("🤖 AI PREDICTION: Tomorrow's AQI expected to be: " + predictedAqi);
            }

            // Find users IN THIS SPECIFIC CITY who are in danger
            List<User> usersAtRisk = userRepository.findByCityAndAqiThresholdLessThanEqual(cityToCheck, liveAqi);

            if (usersAtRisk.isEmpty()) {
                System.out.println("✅ Air is safe for users in " + cityToCheck + ". No alerts sent.");
            } else {
                // Send emails
                for (User user : usersAtRisk) {
                    System.out.println("⚠️ Danger! Sending alert to: " + user.getName());
                    emailService.sendAlertEmail(user.getEmail(), user.getCity(), liveAqi);
                }
            }
        }
        System.out.println("----------------------------------------\n");
    }
}