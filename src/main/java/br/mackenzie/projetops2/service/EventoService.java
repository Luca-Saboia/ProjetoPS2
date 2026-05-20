package br.mackenzie.projetops2.service;

import br.mackenzie.projetops2.dto.WeatherResponseDTO;
import br.mackenzie.projetops2.model.Artista;
import br.mackenzie.projetops2.model.Evento;
import br.mackenzie.projetops2.model.LogPrevisao;
import br.mackenzie.projetops2.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private OpenWeatherService openWeatherService;

    // 1. SALVAR / ATUALIZAR
    public Evento salvar(Evento evento) {
        
        // Passo A: Vincular o relacionamento bidirecional com os artistas
        if (evento.getArtistas() != null) {
            for (Artista artista : evento.getArtistas()) {
                if (artista.getEventos() == null) {
                    artista.setEventos(new ArrayList<>());
                }
                if (!artista.getEventos().contains(evento)) {
                    artista.getEventos().add(evento);
                }
            }
        }

        // Passo B: Consumir a API externa usando o seu método real
        try {
            if (evento.getLocalizacao() != null && evento.getLocalizacao().getCity() != null) {
                String cidade = evento.getLocalizacao().getCity();

                // CHAMADA CORRETA: Utiliza o método exato e o DTO do seu projeto
                WeatherResponseDTO dadosClima = openWeatherService.buscarClimaPorCidade(cidade);

                if (dadosClima != null && dadosClima.getMain() != null && dadosClima.getWeather() != null && !dadosClima.getWeather().isEmpty()) {
                    LogPrevisao log = new LogPrevisao();
                    
                    // Mapeia os dados usando os métodos do seu WeatherResponseDTO
                    log.setTemperatura(dadosClima.getMain().getTemp());
                    log.setUmidade(dadosClima.getMain().getHumidity());
                    log.setCondicaoClimatica(dadosClima.getWeather().get(0).getDescription());
                    log.setDataConsulta(LocalDateTime.now());
                    log.setEvento(evento);

                    if (evento.getLogsPrevisao() == null) {
                        evento.setLogsPrevisao(new ArrayList<>());
                    }
                    evento.getLogsPrevisao().add(log);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao processar integração climática da API: " + e.getMessage());
        }

        // Passo C: Persiste os dados salvos ou editados no banco H2
        return eventoRepository.save(evento);
    }

    // 2. LISTAR TODOS
    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    // 3. BUSCAR POR ID (Nome alinhado com findById do Controller)
    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    // 4. DELETAR POR ID
    public void deletarPorId(Long id) {
        eventoRepository.deleteById(id);
    }
}