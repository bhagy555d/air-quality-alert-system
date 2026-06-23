from flask import Flask, request, jsonify
import joblib
import pandas as pd

app = Flask(__name__)

# 1. Load the trained AI model into memory when the server starts
model = joblib.load('aqi_predictor.pkl')

# 2. Create an API endpoint that Java can talk to
@app.route('/predict', methods=['POST'])
def predict_aqi():
    try:
        # Grab the JSON data sent by the Java backend
        data = request.get_json()
        
        # Convert the incoming JSON into a format the ML model understands
        features = pd.DataFrame([{
            'temp': data['temp'],
            'humidity': data['humidity'],
            'wind_speed': data['wind_speed'],
            'todays_aqi': data['todays_aqi']
        }])
        
        # Ask the model to predict tomorrow's AQI
        prediction = model.predict(features)[0]
        
        # Send the prediction back to Java as JSON
        return jsonify({'predicted_aqi': round(prediction)}), 200

    except Exception as e:
        return jsonify({'error': str(e)}), 400

if __name__ == '__main__':
    print("🚀 Python ML Microservice is running and listening for Java!")
    app.run(port=5000)