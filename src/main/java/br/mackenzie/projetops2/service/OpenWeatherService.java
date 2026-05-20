package br.mackenzie.projetops2.service;

import br.mackenzie.projetops2.dto.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class OpenWeatherService {

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final String baseUrl = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherResponseDTO buscarClimaPorCidade(String cidade) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s?q=%s&appid=%s&units=metric&lang=pt_br", baseUrl, cidade, apiKey);
        System.out.println(url);
        try {
            return restTemplate.getForObject(url, WeatherResponseDTO.class);
        } catch (RestClientException e) {
            System.err.println("Erro na comunicação com o OpenWeatherMap: " + e.getMessage());
            return null;
        }
    }
}