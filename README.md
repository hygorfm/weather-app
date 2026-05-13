# weather-app

Simple Spring Boot project that consumes OpenWeather API to obtain weather information

Given a city name returns the weather for that location

Project created using spring initializr tool with the following dependencies:
- Spring Boot DevTools
- Spring Web
- Lombok

Instructions for docker execution:
1) run "docker build -t weather-app ." inside the project folder to create the docker image
2) run "docker images" to see the image generated
3) run "docker run -d -p 8000:8080 weather-app" to initialize the container in detached mode (-d: background)


Some Improvement Ideas:

1) Add "city" input validation in WeatherResource.weather() method.

2) Call another third party API in WeatherService.getWeather() method.

3) Add unit tests for provider classes, expand tests, improve them, add negative scenarios, etc.

4) Add custom exceptions
