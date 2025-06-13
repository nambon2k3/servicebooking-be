package com.fpt.capstone.tourism.controller;

import com.fpt.capstone.tourism.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/forecast")
    public ResponseEntity<?> getWeatherForecast(@RequestParam(required = false) String location,
                                                @RequestParam(required = false) String date) {
        return ResponseEntity.ok(weatherService.getWeatherForecast(location, date));
    }
}
