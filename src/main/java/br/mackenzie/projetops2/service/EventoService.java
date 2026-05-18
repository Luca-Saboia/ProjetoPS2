package br.mackenzie.projetops2.service;

import br.mackenzie.projetops2.model.LogPrevisao;
import br.mackenzie.projetops2.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final RestTemplate restTemplate;

    @Value("${openweathermap.api.key:sua_chave_aqui}")
    private String apiKey;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
        this.restTemplate = new RestTemplate();
    }

    public LogPrevisao consultarClima(String cidade) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cidade + "&appid=" + apiKey + "&units=metric&lang=pt_br";
        
        var log = new LogPrevisao();
        log.setDataConsulta(LocalDateTime.now());

        try {
            OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);
            if (response != null) {
                log.setTemperatura(response.getTemp());
                log.setUmidade(response.getHumidity());
                log.setDescricaoClima(response.getDescription());
            }
        } catch (Exception e) {
            log.setTemperatura(22.0); 
            log.setDescricaoClima("Indisponivel (Offline)");
            log.setUmidade(50);
        }
        return log;
    }
}