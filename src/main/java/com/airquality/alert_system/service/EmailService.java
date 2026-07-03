package com.airquality.alert_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendAlertEmail(String toEmail, String city, int currentAqi) {
        SimpleMailMessage message = new SimpleMailMessage();

       
        message.setFrom("dhakatebhagyashree179@gmail.com");

        message.setTo(toEmail);
        message.setSubject("Air Quality Alert: " + city);
        message.setText("Hello,\n\n" +
                "The current Air Quality Index (AQI) in " + city + " has reached " + currentAqi + ".\n" +
                "This exceeds your safe health threshold.\n\n" +
                "Please consider wearing a mask or staying indoors.\n\n" +
                "- Your Air Quality Alert Dashboard");

        mailSender.send(message);
        System.out.println("✅ Alert email successfully sent to: " + toEmail);
    }
}