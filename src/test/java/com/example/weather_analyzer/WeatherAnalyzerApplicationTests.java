package com.example.weather_analyzer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.weather_analyzer.util.WeatherApiClient;

@SpringBootTest
class WeatherAnalyzerApplicationTests {

  @Autowired
    private WeatherApiClient weatherApiClient;

    @Test
    public void testGetWeatherData() {
        String city = "London";
        String response = weatherApiClient.getWeatherData(city);

        // Print the response or add assertions to verify the data
        System.out.println(response);

        // Example assertion
        // Assertions.assertNotNull(response);
        // Assertions.assertTrue(response.contains("London"));
    }

}
