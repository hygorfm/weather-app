package com.weather.demo.weather_app.service;

import com.weather.demo.weather_app.domain.CityCoordinates;
import com.weather.demo.weather_app.domain.WeatherRequestDetails;
import com.weather.demo.weather_app.entity.GeocodingCoordinatesEntity;
import com.weather.demo.weather_app.entity.OpenWeatherResponseEntity;
import com.weather.demo.weather_app.entity.WeatherEntity;
import com.weather.demo.weather_app.entity.WeatherResponse;
import com.weather.demo.weather_app.provider.GeocodingProvider;
import com.weather.demo.weather_app.provider.WeatherProvider;
import com.weather.demo.weather_app.transformer.GeocodingCoordinatesTransformer;
import com.weather.demo.weather_app.transformer.OpenWeatherTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherService.class)
class WeatherServiceTest {

    public static final String CITY = "London";
    public static final String LATITUDE = "11.98";
    public static final String LONGITUDE = "34.89";
    public static final String WEATHER = "Rain";
    public static final String DETAILS = "a lot of rain";

    @MockitoBean
    private GeocodingProvider geocodingProvider;
    @MockitoBean
    private WeatherProvider weatherProvider;
    @MockitoBean
    private GeocodingCoordinatesTransformer geocodingCoordinatesTransformer;
    @MockitoBean
    private OpenWeatherTransformer openWeatherTransformer;

    @InjectMocks
    private WeatherService weatherService;


    @Test
    public void test_should_return_weather_response() throws Exception {
        final WeatherRequestDetails requestDetails = WeatherRequestDetails.builder()
                .city(CITY)
                .build();

        mockGeocodingProvider(requestDetails);
        mockGeocodingCoordinatesTransformer();
        mockWeatherProvider();
        mockOpenWeatherTransformer();

        final WeatherResponse weatherResponse = weatherService.getWeather(requestDetails);

        assertAll("Should return city weather response",
                () -> assertEquals(WEATHER, weatherResponse.getWeather()),
                () -> assertEquals(DETAILS, weatherResponse.getDetails()));
    }

    private void mockGeocodingProvider(WeatherRequestDetails requestDetails) throws Exception {
        final GeocodingCoordinatesEntity entity = GeocodingCoordinatesEntity.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        when(geocodingProvider.getCoordinates(requestDetails)).thenReturn(entity);
    }

    private void mockGeocodingCoordinatesTransformer() {
        final CityCoordinates cityCoordinates = CityCoordinates.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        when(geocodingCoordinatesTransformer.transformToDomain(any())).thenReturn(cityCoordinates);
    }

    private void mockWeatherProvider() throws Exception {
        final WeatherEntity weatherEntity = WeatherEntity.builder()
                .main(WEATHER)
                .description(DETAILS)
                .build();
        final WeatherEntity[] weatherEntities = {weatherEntity};
        final OpenWeatherResponseEntity entity = OpenWeatherResponseEntity.builder()
                .weather(weatherEntities)
                .build();

        when(weatherProvider.getWeather(any())).thenReturn(entity);
    }

    private void mockOpenWeatherTransformer() {
        final WeatherResponse weatherResponse = WeatherResponse.builder()
                .weather(WEATHER)
                .details(DETAILS)
                .build();

        when(openWeatherTransformer.transformToEntity(any())).thenReturn(weatherResponse);
    }

}