package com.airquality.alert_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

// @Service tells Spring Boot that this class contains our core business logic.
@Service
public class EmailService {

    // Spring Boot automatically configures this using the Gmail credentials
    // we put in the application.properties file!
    @Autowired
    private JavaMailSender mailSender;

    public void sendAlertEmail(String toEmail, String city, int currentAqi) {
        SimpleMailMessage message = new SimpleMailMessage();

        // This sets up the actual email structure
        message.setTo(toEmail);
        message.setSubject("⚠️ Air Quality Alert: " + city);
        message.setText("Hello,\n\n" +
                "The current Air Quality Index (AQI) in " + city + " has reached " + currentAqi + ".\n" +
                "This exceeds your safe health threshold.\n\n" +
                "Please consider wearing a mask or staying indoors.\n\n" +
                "- Your Air Quality Alert Dashboard");

        // Send the email and print a confirmation to our VS Code terminal
        mailSender.send(message);
        System.out.println("Alert email successfully sent to: " + toEmail);
    }
}