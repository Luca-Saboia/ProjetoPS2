package br.mackenzie.projetops2.controller;

import br.mackenzie.projetops2.model.Evento;
import br.mackenzie.projetops2.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoApiController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        Evento novoEvento = eventoService.salvar(evento);
        // Retorno correto com o código HTTP 201 (Created)
        return new ResponseEntity<>(novoEvento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listarTodos() {
        return new ResponseEntity<>(eventoService.listarTodos(), HttpStatus.OK);
    }
}