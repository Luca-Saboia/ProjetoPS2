package br.mackenzie.projetops2.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDTO {

    @JsonProperty("main")
    private MainData main;

    @JsonProperty("weather")
    private List<WeatherDescription> weather;

    public MainData getMain() { return main; }
    public void setMain(MainData main) { this.main = main; }
    public List<WeatherDescription> getWeather() { return weather; }
    public void setWeather(List<WeatherDescription> weather) { this.weather = weather; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainData {
        @JsonProperty("temp")
        private Double temp;

        @JsonProperty("humidity")
        private Integer humidity;

        public Double getTemp() { return temp; }
        public void setTemp(Double temp) { this.temp = temp; }
        public Integer getHumidity() { return humidity; }
        public void setHumidity(Integer humidity) { this.humidity = humidity; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherDescription {
        @JsonProperty("description")
        private String description;

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }
}