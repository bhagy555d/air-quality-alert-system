import pandas as pd
from sklearn.ensemble import RandomForestRegressor
import joblib

# 1. Create sample historical weather and pollution data
# Features: [Temperature (°C), Humidity (%), WindSpeed (km/h), Today's AQI]
data = {
    'temp': [35, 36, 28, 40, 32, 30, 38, 25, 22, 42],
    'humidity': [40, 45, 80, 30, 60, 70, 35, 85, 90, 20],
    'wind_speed': [10, 12, 5, 15, 8, 6, 14, 4, 3, 18],
    'todays_aqi': [120, 130, 80, 150, 95, 85, 140, 60, 50, 170],
    # Target: What the AQI actually was the NEXT day
    'tomorrows_aqi': [125, 135, 75, 160, 90, 80, 145, 55, 45, 180] 
}

df = pd.DataFrame(data)

# 2. Split into Features (What we know) and Target (What we want to predict)
X = df[['temp', 'humidity', 'wind_speed', 'todays_aqi']]
y = df['tomorrows_aqi']

# 3. Train the Machine Learning Model
print("⚙️ Training the Random Forest AI model...")
model = RandomForestRegressor(n_estimators=100, random_state=42)
model.fit(X, y)

# 4. Save the trained "brain" to a file so we don't have to retrain it every time
joblib.dump(model, 'aqi_predictor.pkl')
print("✅ Model trained and successfully saved as 'aqi_predictor.pkl'")