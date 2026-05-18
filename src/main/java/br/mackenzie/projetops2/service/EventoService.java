package br.mackenzie.projetops2.service;

import br.mackenzie.projetops2.model.Evento;
import br.mackenzie.projetops2.model.LogPrevisao;
import br.mackenzie.projetops2.dto.WeatherResponseDTO;
import br.mackenzie.projetops2.repository.EventoRepository;
import br.mackenzie.projetops2.repository.LogPrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private LogPrevisaoRepository logPrevisaoRepository;

    @Autowired
    private OpenWeatherService openWeatherService;

    // Método solicitado pelo EventoApiController (Linha 20)
    public Evento salvarEvento(Evento evento) {
        // Persiste as entidades locais vinculadas em cascata
        Evento eventoSalvo = eventoRepository.save(evento);

        // Dispara o consumo da API externa
        String cidade = eventoSalvo.getLocalizacao().getCity();
        WeatherResponseDTO dadosClima = openWeatherService.buscarClimaPorCidade(cidade);

        // Armazenamento em banco local para consulta offline (Conforme requisito do enunciado)
        if (dadosClima != null) {
            LogPrevisao log = new LogPrevisao();
            log.setTemperatura(dadosClima.getMain().getTemp());
            log.setCondicaoClimatica(dadosClima.getWeather().get(0).getDescription());
            log.setUmidade(dadosClima.getMain().getHumidity());
            log.setDataConsulta(LocalDateTime.now());
            log.setEvento(eventoSalvo);

            logPrevisaoRepository.save(log);
        }

        return eventoSalvo;
    }

    // Método solicitado pelo EventoApiController (Linha 27) e EventoWebController (Linha 20)
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }
}