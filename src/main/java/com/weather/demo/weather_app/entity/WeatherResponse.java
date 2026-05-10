package com.weather.demo.weather_app.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherResponse {

    private String weather;
    private String details;

}
