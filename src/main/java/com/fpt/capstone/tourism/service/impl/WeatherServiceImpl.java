package com.fpt.capstone.tourism.service.impl;

import com.fpt.capstone.tourism.dto.common.GeneralResponse;
import com.fpt.capstone.tourism.exception.common.BusinessException;
import com.fpt.capstone.tourism.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {


    @Value("${weather.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.weatherapi.com/v1/future.json";

    private final RestTemplate restTemplate;

    @Override
    public GeneralResponse<?> getWeatherForecast(String location, String date) {
        String url = String.format("%s?key=%s&q=%s&days=7&aqi=no&alerts=no&dt=%s", BASE_URL, apiKey, location, date);
        try {
            String response = restTemplate.getForObject(url, String.class);
            return GeneralResponse.of(response);
        } catch (Exception e) {
            throw BusinessException.of("Error calling Weather API", e);
        }
    }
}
