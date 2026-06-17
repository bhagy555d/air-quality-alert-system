package com.airquality.alert_system.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AqiService {

    // This grabs your secret token directly from application.properties!
    @Value("${waqi.api.token}")
    private String apiToken;

    public int getRealTimeAqi(String city) {
        try {
            // 1. Build the exact URL for the city requested
            String url = "https://api.waqi.info/feed/" + city + "/?token=" + apiToken;

            // 2. Make the HTTP GET request to the internet
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);

            // 3. Parse the JSON response we got back
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);

            // 4. Extract the AQI number if the request was successful
            if ("ok".equals(root.path("status").asText())) {
                return root.path("data").path("aqi").asInt();
            } else {
                System.out.println("⚠️ WAQI API Error: " + root.path("data").asText());
            }

        } catch (Exception e) {
            System.out.println("❌ Connection Error fetching AQI for " + city + ": " + e.getMessage());
        }

        // Return -1 if something went wrong, so our system knows it's invalid data
        return -1;
    }
}