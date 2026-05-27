package br.mackenzie.projetops2.controller;

import br.mackenzie.projetops2.model.Evento;
import br.mackenzie.projetops2.model.LogPrevisao;
import br.mackenzie.projetops2.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/estatisticas")
public class AtividadeSalaController {
    @Autowired
    private EventoService eventoService;
    @GetMapping("/clima/risco")
    public Map<String, Object> calcularRisco() {
        var eventos = eventoService.listarTodos();
        int total = eventos.size();
        int comRisco = 0;
        for (Evento e : eventos) {
            if (e.getLogsPrevisao() != null) {
                for (LogPrevisao log : e.getLogsPrevisao()) {
                    String clima = log.getCondicaoClimatica().toLowerCase();
                    if (clima.contains("chuva") || clima.contains("rain") || clima.contains("storm")) {
                        comRisco++;
                        break; 
                    }
                }
            }
        }
        double percentual = (total == 0) ? 0.0 : (comRisco * 100.0) / total;
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("totalEventos", total);
        resposta.put("percentualRisco", percentual);
        return resposta;
    }
}