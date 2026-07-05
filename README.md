🌍 AI-Powered Air Quality & Health Alert System

> An intelligent, real-time environmental monitoring dashboard featuring predictive analytics and personalized health alerts.

## 📖 Project Overview
This project is an advanced, distributed web application designed to monitor real-time Air Quality Index (AQI) data across major cities (e.g., Nagpur, Pune, Mumbai). Going beyond standard data visualization, this system integrates scheduled third-party API polling, machine learning-based forecasting, and a localized notification engine that pushes health alerts to vulnerable users when pollution crosses specific safety thresholds.

## ✨ Key Features
* **Real-Time Data Integration:** Automated hourly background tasks fetch live pollution metrics via the World Air Quality Index (WAQI) API.
* **Personalized Health Notifications:** A subscription-based alert system that notifies users (via Email/SMS) when the AQI poses a threat to their specific health conditions (e.g., Asthma, Elderly).
* **Predictive Analytics:** A machine learning module to forecast short-term pollution trends based on historical time-series data.
* **Interactive Dashboard:** A dynamic Single Page Application (SPA) visualizing current metrics and historical trends using robust charting libraries.
* **Cloud-Native Architecture:** Fully deployed microservice architecture utilizing environment variables for secure credential management.

## 🛠️ Technology Stack
* **Frontend:** HTML5 / CSS3 / Vanilla JavaScript (fetch API)
* **Backend:** Java (Spring Boot) / Hibernate ORM
* **Database:** MySQL (Cloud-Hosted via Aiven)
* **Machine Learning:** Python / Flask / Scikit-Learn (RandomForestRegressor)
* **External APIs:** WAQI API (Live Data), SendGrid (SMTP Email Engine)
* **Cloud & DevOps:** Render (PaaS), Cloud Environment Variables

## 🏗️ System Architecture
1. **Data Ingestion:** A Spring Boot `@Scheduled` task queries the WAQI API hourly.
2. **Notification Engine:** A background worker evaluates the latest AQI data against registered user health profiles.
3. **SMTP Gateway:** If thresholds are breached, the system authenticates with SendGrid via injected environment variables to dispatch alerts.
4. **Client Interface:** The frontend consumes RESTful endpoints provided by the Java backend to render localized data and AI predictions.

## 🗺️ Project Roadmap
- [x] Define project scope and repository setup.
- [ ] **Phase 1:** Design SQL schema and implement the Java user notification engine.
- [ ] **Phase 2:** Integrate the WAQI API and build the automated hourly data fetcher.
- [ ] **Phase 3:** Develop the interactive frontend dashboard.
- [ ] **Phase 4:** Build and train the Python predictive ML model.
- [ ] **Phase 5:** Migrate database to the cloud and deploy the Spring Boot microservice.

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

### **Phase 3: Frontend Web UI — COMPLETE ✅**
- **User Interface:** Built a responsive, modern HTML5/CSS3 registration portal for users to subscribe to AQI alerts.
- **Client-Server Communication:** Implemented asynchronous JavaScript (`fetch` API) to send JSON payloads to the backend without page reloads.
- **Full-Stack Integration:** Successfully connected the frontend web client to the Spring Boot `@RestController`, enabling seamless database insertion and real-time UI feedback.

### **Phase 4: Full-Stack AI Integration & Live Search Dashboard — COMPLETE ✅**
- **Machine Learning Microservice:** Engineered a standalone Python/Flask server hosting a `RandomForestRegressor` trained to predict future AQI based on meteorological data.
- **Dynamic Global Scanner:** Upgraded the Spring Boot cron scheduler to dynamically fetch unique user cities using custom Hibernate `@Query` methods, optimizing API calls and automating personalized email alerts.
- **Live Search REST API:** Built a dedicated Spring Boot controller to asynchronously route frontend queries to the WAQI satellite API and the local Python AI server.
- **Interactive Web Dashboard:** Designed a responsive frontend UI using HTML, CSS, and vanilla JavaScript (`fetch` API) allowing users to search any global city and instantly view real-time pollution levels alongside tomorrow's AI prediction.

### **Phase 5: Cloud Deployment & Architecture Optimization — COMPLETE ✅**
- **Cloud Database Migration:** Successfully migrated the local relational database to an Aiven Cloud MySQL instance, ensuring high availability.
- **PaaS Deployment:** Deployed the Spring Boot application to Render, establishing a continuous live background worker for the scheduling engine.
- **Security & Configuration:** Decoupled sensitive credentials from the codebase by injecting API keys, database URIs, and SMTP passwords via Render Environment Variables.
- **Strategic Vendor Pivot:** Re-engineered the SMTP pipeline, migrating from a rigid legacy provider to SendGrid to bypass strict ISP blocks and ensure reliable, real-time alert delivery.
