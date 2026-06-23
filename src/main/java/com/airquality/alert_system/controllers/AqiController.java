package com.airquality.alert_system.controllers;

import com.airquality.alert_system.service.AqiService;
import com.airquality.alert_system.service.MlPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/aqi")
@CrossOrigin(origins = "*") // Allows your HTML file to talk to this Java server
public class AqiController {

    @Autowired
    private AqiService aqiService;

    @Autowired
    private MlPredictionService mlPredictionService;

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchCity(@RequestParam String city) {
        Map<String, Object> response = new HashMap<>();

        // 1. Get Live Data from the Internet
        int liveAqi = aqiService.getRealTimeAqi(city);
        if (liveAqi == -1) {
            response.put("error", "Could not find AQI data for " + city + ". Check your spelling!");
            return ResponseEntity.badRequest().body(response);
        }

        // 2. Get AI Prediction from Python
        int predictedAqi = mlPredictionService.getPredictedTomorrowAqi(liveAqi);

        // 3. Package and send back to the web browser
        response.put("city", city.toUpperCase());
        response.put("liveAqi", liveAqi);
        response.put("predictedAqi", predictedAqi);

        return ResponseEntity.ok(response);
    }
}