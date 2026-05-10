package com.weather.demo.weather_app.resource;

import com.weather.demo.weather_app.domain.WeatherRequestDetails;
import com.weather.demo.weather_app.entity.WeatherResponse;
import com.weather.demo.weather_app.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherResource.class)
class WeatherResourceTest {

    public static final String CITY = "London";
    public static final String WEATHER = "Sunny";
    public static final String DETAILS = "very sunny";

    @MockitoBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test_should_return_weather_response_sucess() throws Exception {
        final WeatherRequestDetails requestDetails = WeatherRequestDetails.builder()
                .city(CITY)
                .build();

        final WeatherResponse weatherResponse = WeatherResponse.builder()
                .weather(WEATHER)
                .details(DETAILS)
                .build();

        when(weatherService.getWeather(requestDetails)).thenReturn(weatherResponse);

        mockMvc.perform(get("/api/v1/weather/{city}", CITY))
                .andDo(print())
                .andExpect(status().isOk());
    }

}