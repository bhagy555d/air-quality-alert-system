# 🌍 Automated Air Quality & Health Alert System

> An intelligent, real-time environmental monitoring dashboard featuring predictive analytics and personalized health alerts. 

## 📖 Project Overview
This project is an advanced, distributed web application designed to monitor real-time Air Quality Index (AQI) data across major cities (e.g., Nagpur, Pune, Mumbai). Going beyond standard data visualization, this system integrates scheduled third-party API polling, machine learning-based forecasting, and a localized notification engine that pushes health alerts to vulnerable users when pollution crosses specific safety thresholds.

## ✨ Key Features
* **Real-Time Data Integration:** Automated hourly background tasks fetch live pollution metrics via the World Air Quality Index (WAQI) API.
* **Personalized Health Notifications:** A subscription-based alert system that notifies users (via Email/SMS) when the AQI poses a threat to their specific health conditions (e.g., Asthma, Elderly).
* **Predictive Analytics:** A machine learning module to forecast short-term pollution trends based on historical time-series data.
* **Interactive Dashboard:** A dynamic Single Page Application (SPA) visualizing current metrics and historical trends using robust charting libraries.

## 🛠️ Technology Stack
* **Frontend:** React.js / HTML5 / CSS3 / Chart.js
* **Backend:** Java (Spring Boot)
* **Database:** MySQL / PostgreSQL
* **Machine Learning:** Python (Scikit-Learn / Pandas)
* **External APIs:** WAQI API, JavaMail / Twilio (Notifications)
* **DevOps:** Docker (Containerization)

## 🏗️ System Architecture
1. **Data Ingestion:** A Spring Boot `@Scheduled` task queries the WAQI API hourly and persists the JSON payload into the SQL database.
2. **Notification Engine:** A background worker evaluates the latest AQI data against registered user health profiles and dispatches alerts if thresholds are breached.
3. **Client Interface:** The React frontend consumes RESTful endpoints provided by the Java backend to render localized, interactive charts.

## 🗺️ Project Roadmap
- [x] Define project scope and repository setup.
- [ ] **Phase 1:** Design SQL schema and implement the Java user notification engine.
- [ ] **Phase 2:** Integrate the WAQI API and build the automated hourly data fetcher.
- [ ] **Phase 3:** Develop the interactive frontend dashboard.
- [ ] **Phase 4:** Build and train the Python predictive ML model.
- [ ] **Phase 5:** Dockerize the application and deploy it to the cloud.

## 🚀 Project Progress Tracker

### **Phase 1: Core Backend & Notification Engine — COMPLETE ✅**
- **Database Architecture:** Configured local MySQL instance (`air_quality_db`) with automatic table schema generation via Hibernate ORM.
- **Data Layer:** Developed `User` and `AqiLog` JPA Entities alongside customized `JpaRepository` interfaces for streamlined database operations.
- **REST API Endpoint:** Implemented a `UserController` featuring user registration capabilities and an isolated testing endpoint for connection validation.
- **Automated Notification Service:** Configured JavaMailSender using secure Google App Passwords to successfully deliver real-time, template-driven email alerts.

### **Phase 2: Real-Time API Integration — COMPLETE ✅**
- **Live Data Fetching:** Engineered a RESTful `AqiService` utilizing Spring Boot's `RestTemplate` to autonomously fetch live environmental data.
- **External API Integration:** Successfully connected to the World Air Quality Index (WAQI) global API network.
- **JSON Parsing:** Implemented `Jackson ObjectMapper` to parse complex JSON payloads and extract precise, real-time AQI metrics.
- **Dynamic Logic:** Upgraded the `@Scheduled` automation engine to trigger conditional database queries and email alerts based strictly on live API responses.
