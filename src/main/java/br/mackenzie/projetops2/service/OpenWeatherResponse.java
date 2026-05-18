package br.mackenzie.projetops2.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

// Mapeia a estrutura básica que o OpenWeatherMap envia de volta
public class OpenWeatherResponse {
    @JsonProperty("main")
    private Map<String, Object> main;
    
    @JsonProperty("weather")
    private List<Map<String, Object>> weather;

    public Double getTemp() {
        if(main != null && main.get("temp") != null) {
            return Double.parseDouble(main.get("temp").toString());
        }
        return 0.0;
    }

    public Integer getHumidity() {
        if(main != null && main.get("humidity") != null) {
            return Integer.parseInt(main.get("humidity").toString());
        }
        return 0;
    }

    public String getDescription() {
        if(weather != null && !weather.isEmpty()) {
            return weather.get(0).get("main").toString();
        }
        return "Desconhecido";
    }
}