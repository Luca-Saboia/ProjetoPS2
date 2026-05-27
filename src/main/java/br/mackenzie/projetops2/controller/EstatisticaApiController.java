package br.mackenzie.projetops2.controller;

import br.mackenzie.projetops2.model.Evento;
import br.mackenzie.projetops2.model.LogPrevisao;
import br.mackenzie.projetops2.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EstatisticaApiController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/api/estatisticas/clima/risco")
    public ResponseEntity<Map<String, Object>> obterEstatisticaRisco() {
        
        List<Evento> eventos = eventoRepository.findAll();
        long totalEventos = eventos.size();
        
        double percentual = 0.0;
        
        if (totalEventos > 0) {
            long eventosComRisco = 0;

            for (Evento evento : eventos) {
                if (evento.getLogsPrevisao() != null) {
                    for (LogPrevisao log : evento.getLogsPrevisao()) {
                        if (log.getCondicaoClimatica() != null) {
                            String condicao = log.getCondicaoClimatica().toLowerCase();
                            
                            if (condicao.contains("chuva") || condicao.contains("tempestade") || 
                                condicao.contains("rain") || condicao.contains("storm")) {
                                eventosComRisco++;
                                break; // Já identificou risco neste evento, pula para o próximo
                            }
                        }
                    }
                }
            }
            // Cálculo da porcentagem utilizando comandos básicos
            percentual = (double) (eventosComRisco * 100) / totalEventos;
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("totalEventos", totalEventos);
        resposta.put("percentualRisco", percentual);

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}