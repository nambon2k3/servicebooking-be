package com.fpt.capstone.tourism.service;

import com.fpt.capstone.tourism.dto.common.GeneralResponse;

public interface WeatherService {
    GeneralResponse<?> getWeatherForecast(String location, String date);
}
