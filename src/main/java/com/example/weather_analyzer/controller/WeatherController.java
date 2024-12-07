package com.example.weather_analyzer.controller;
import com.example.weather_analyzer.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city) {
    return weatherService.getWeatherSummary(city);
    }

    
}
