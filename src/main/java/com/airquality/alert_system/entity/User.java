package com.airquality.alert_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // This tells MySQL to name the table "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true) // Ensures no duplicate emails
    private String email;

    private String city; // e.g., "Nagpur", "Mumbai"

    private int aqiThreshold; // The danger level for this specific user (e.g., 100)

    // ==========================================
    // Getters and Setters (Required for Spring)
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id; // The typo was fixed here
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAqiThreshold() {
        return aqiThreshold;
    }

    public void setAqiThreshold(int aqiThreshold) {
        this.aqiThreshold = aqiThreshold;
    }
}