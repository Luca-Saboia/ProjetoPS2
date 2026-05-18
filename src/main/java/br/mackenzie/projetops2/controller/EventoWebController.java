package br.mackenzie.projetops2.controller;

import br.mackenzie.projetops2.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // <-- Certifique-se de importar o Controller tradicional
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //  SE ESTIVER @RestController, MUDE PARA @Controller
@RequestMapping("/web/eventos")
public class EventoWebController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String listarEventos(Model model) {
        // Envia a lista de eventos para o HTML do Thymeleaf
        model.addAttribute("eventos", eventoService.listarTodos());
        
        return "eventos"; // Retorna o nome do arquivo eventos.html dentro de templates
    }
}