package com.weather.demo.weather_app.transformer;

import com.weather.demo.weather_app.domain.CityCoordinates;
import com.weather.demo.weather_app.entity.GeocodingCoordinatesEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeocodingCoordinatesTransformerTest {

    private GeocodingCoordinatesTransformer transformer;

    @BeforeEach
    public void setup() {
        transformer = new GeocodingCoordinatesTransformer();
    }

    @Test
    public void test_should_transform_geocoding_coordinates_to_domain() {
        final GeocodingCoordinatesEntity entity = GeocodingCoordinatesEntity.builder()
                .latitude("12.0")
                .longitude("12.90")
                .build();

        final CityCoordinates cityCoordinates = transformer.transformToDomain(entity);

        assertAll("Should return domain city coordinates",
                () -> assertEquals(entity.getLatitude(), cityCoordinates.getLatitude()),
                () -> assertEquals(entity.getLongitude(), cityCoordinates.getLongitude()));
    }

}