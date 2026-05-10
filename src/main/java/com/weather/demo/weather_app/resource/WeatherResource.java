package com.weather.demo.weather_app.resource;

import com.weather.demo.weather_app.domain.WeatherRequestDetails;
import com.weather.demo.weather_app.entity.WeatherResponse;
import com.weather.demo.weather_app.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WeatherResource {

    private WeatherService weatherService;

    @Autowired
    public WeatherResource(final WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    @GetMapping("/weather/{city}")
    public @ResponseBody WeatherResponse weather(@PathVariable("city") String city) throws Exception {
        final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder()
                .city(city)
                .build();

        return weatherService.getWeather(weatherRequestDetails);
    }

}
