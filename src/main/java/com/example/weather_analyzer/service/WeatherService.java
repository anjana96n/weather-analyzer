package com.example.weather_analyzer.service;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    public String getWeatherSummary(String city) {
        return "Weather summary for city: " + city; 
    }
}
