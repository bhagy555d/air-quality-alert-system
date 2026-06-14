package com.airquality.alert_system.controllers;

import com.airquality.alert_system.entity.User;
import com.airquality.alert_system.repository.UserRepository;
import com.airquality.alert_system.service.EmailService; // Added this import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Here is where we bring in the EmailService!
    @Autowired
    private EmailService emailService;

    // 1. Endpoint to ADD a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    // 2. Endpoint to VIEW all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3. Temporary endpoint to test our Gmail connection
    @PostMapping("/test-email")
    public String testEmail(@RequestParam String email) {
        // We hardcode a fake city and a dangerously high AQI just to trigger the
        // warning format
        emailService.sendAlertEmail(email, "Testville", 250);
        return "Success! Check the inbox of: " + email;
    }
}