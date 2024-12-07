package com.example.weather_analyzer.service;

import com.example.weather_analyzer.model.WeatherSummary;
import com.example.weather_analyzer.util.WeatherApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {

    @Autowired
    private WeatherApiClient weatherApiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherSummary getWeatherSummary(String city) {
        // Fetch weather data from OpenWeatherMap
        String apiResponse = weatherApiClient.getWeatherData(city);

        // Parse the response using Jackson's ObjectMapper
        JsonNode responseJson;
        try {
            responseJson = objectMapper.readTree(apiResponse);  // Parse the JSON response
        } catch (IOException e) {
            throw new RuntimeException("Error parsing weather data: " + e.getMessage());
        }

        // Extract the list of weather data from the parsed JSON
        JsonNode list = responseJson.get("list");

        // Initialize variables to hold daily temperature data
        Map<LocalDate, Double> dailyTemperatures = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Loop through the weather entries and aggregate temperatures by day
        for (JsonNode weatherEntry : list) {
            String dateString = weatherEntry.get("dt_txt").asText().split(" ")[0];
            LocalDate date = LocalDate.parse(dateString, formatter);
            double temp = weatherEntry.get("main").get("temp").asDouble();

            // Calculate daily average temperature
            dailyTemperatures.put(date, dailyTemperatures.getOrDefault(date, 0.0) + temp);
        }

        // Compute summary metrics (average temperature, hottest day, coldest day)
        double averageTemperature = dailyTemperatures.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        LocalDate hottestDay = dailyTemperatures.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        LocalDate coldestDay = dailyTemperatures.entrySet().stream().min(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);

        // Return the weather summary
        return new WeatherSummary(city, averageTemperature, hottestDay.toString(), coldestDay.toString());
    }

}
