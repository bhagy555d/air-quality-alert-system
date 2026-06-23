package com.airquality.alert_system.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class MlPredictionService {

    public int getPredictedTomorrowAqi(int todaysLiveAqi) {
        try {
            // Pointing directly to your active Python server
            String pythonApiUrl = "http://127.0.0.1:5000/predict";

            Map<String, Integer> requestData = new HashMap<>();
            requestData.put("temp", 35);
            requestData.put("humidity", 40);
            requestData.put("wind_speed", 10);
            requestData.put("todays_aqi", todaysLiveAqi);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> response = restTemplate.postForEntity(pythonApiUrl, requestData, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // FIX: We use 'Number' here so Java doesn't panic whether Python sends 80 or
                // 80.5!
                Number predictedAqi = (Number) response.getBody().get("predicted_aqi");
                return predictedAqi.intValue();
            }

        } catch (Exception e) {
            System.out.println("❌ Could not reach Python ML Service: " + e.getMessage());
        }

        return -1;
    }
}