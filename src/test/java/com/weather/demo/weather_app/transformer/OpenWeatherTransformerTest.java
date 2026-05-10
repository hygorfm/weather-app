package com.weather.demo.weather_app.transformer;

import com.weather.demo.weather_app.domain.CityWeather;
import com.weather.demo.weather_app.entity.OpenWeatherResponseEntity;
import com.weather.demo.weather_app.entity.WeatherEntity;
import com.weather.demo.weather_app.entity.WeatherResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherTransformerTest {

    private OpenWeatherTransformer transformer;

    @BeforeEach
    public void setup() {
        transformer = new OpenWeatherTransformer();
    }

    @Test
    public void test_should_transform_open_weather_response_entity_to_domain() {
        final WeatherEntity weatherEntity = WeatherEntity.builder()
                .main("Rain")
                .description("a lot of rain")
                .build();
        final WeatherEntity[] weatherEntities = {weatherEntity};
        final OpenWeatherResponseEntity entity = OpenWeatherResponseEntity.builder()
                .weather(weatherEntities)
                .build();

        CityWeather cityWeather = transformer.transformToDomain(entity);

        assertAll("Should return domain weather object",
                () -> assertEquals(entity.getWeather()[0].getMain(), cityWeather.getWeather()),
                () -> assertEquals(entity.getWeather()[0].getDescription(), cityWeather.getDetails()));
    }

    @Test
    public void test_should_transform_city_weather_to_entity() {
        final CityWeather cityWeather = CityWeather.builder()
                .weather("Rain")
                .details("a lot of rain")
                .build();

        final WeatherResponse weatherResponse = transformer.transformToEntity(cityWeather);

        assertAll("Should return entity weather response object",
                () -> assertEquals(cityWeather.getWeather(), weatherResponse.getWeather()),
                () -> assertEquals(cityWeather.getDetails(), weatherResponse.getDetails()));
    }

}