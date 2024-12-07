package com.example.weather_analyzer.util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WeatherApiClient {
    private final WebClient webClient;
    private final String apiKey;

    public WeatherApiClient(@Value("${weather.api.base-url}") String baseUrl,
                            @Value("${weather.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
        this.apiKey = apiKey;
    }

    public String getWeatherData(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block(); // Change to async later
    }
}
