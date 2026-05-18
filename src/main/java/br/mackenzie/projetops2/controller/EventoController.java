package br.mackenzie.projetops2.controller;

import br.mackenzie.projetops2.model.Evento;
import br.mackenzie.projetops2.model.Artista;
import br.mackenzie.projetops2.repository.EventoRepository;
import br.mackenzie.projetops2.repository.ArtistaRepository;
import br.mackenzie.projetops2.service.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;
    private final ArtistaRepository artistaRepository;
    private final EventoService eventoService;

    // Construtor injetando os Repositories e o Service de Clima
    public EventoController(EventoRepository eventoRepository, ArtistaRepository artistaRepository, EventoService eventoService) {
        this.eventoRepository = eventoRepository;
        this.artistaRepository = artistaRepository;
        this.eventoService = eventoService;
    }

    @GetMapping
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        var evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            return ResponseEntity.ok(evento.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        // Antes de salvar, consulta a API externa de clima com base na cidade do endereço
        if (evento.getEndereco() != null && evento.getEndereco().getCidade() != null) {
            var clima = eventoService.consultarClima(evento.getEndereco().getCidade());
            evento.setPrevisaoDoTempo(clima);
            
            // Regra de negócio do Status baseada no clima da API externa
            if (clima.getDescricaoClima().equalsIgnoreCase("Rain") || clima.getDescricaoClima().equalsIgnoreCase("Chuva")) {
                evento.setStatus("Alerta de Chuva");
            } else {
                evento.setStatus("Confirmado");
            }
        }
        
        Evento saved = eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento eventoUpdate) {
        var evento = eventoRepository.findById(id);
        if (evento.isPresent()) {
            var existingEvento = evento.get();
            existingEvento.setDataEvento(eventoUpdate.getDataEvento());
            existingEvento.setPreco(eventoUpdate.getPreco());
            
            var updated = eventoRepository.save(existingEvento);
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint de Associação idêntico ao do exemplo do Professor (/trainers/{id}/pokemons/{pokemonId})
    // No seu caso, associa um Artista a um Evento existente
    @PostMapping("/{id}/artistas/{artistaId}")
    public ResponseEntity<Evento> addArtistaToEvento(@PathVariable Long id, @PathVariable Long artistaId) {
        var evento = eventoRepository.findById(id);
        if (!evento.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var artista = artistaRepository.findById(artistaId);
        if (!artista.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        evento.get().setArtista(artista.get());
        eventoRepository.save(evento.get());
        return ResponseEntity.ok(evento.get());
    }
}