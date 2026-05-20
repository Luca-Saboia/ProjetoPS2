package br.mackenzie.projetops2.controller;

import br.mackenzie.projetops2.model.Evento;
import br.mackenzie.projetops2.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/eventos")
public class EventoWebController {

    @Autowired
    private EventoService eventoService;

    // 1. LISTAR EVENTOS
    @GetMapping
    public String listarEventos(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos());
        return "eventos"; 
    }

    // 2. EXIBIR FORMULÁRIO DE CADASTRO
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("evento", new Evento()); 
        return "cadastro"; 
    }

    // 3. EXIBIR FORMULÁRIO DE EDIÇÃO (Corrigido para usar findById)
    @GetMapping("/editar/{id}")
    public String exibirFormularioEdicao(@PathVariable Long id, Model model) {
        Evento evento = eventoService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento inválido: " + id));
        model.addAttribute("evento", evento); 
        return "cadastro"; 
    }

    // 4. PROCESSAR SALVAMENTO (Redirecionamento Relativo Seguro)
    @PostMapping("/salvar")
    public String salvarEvento(@ModelAttribute("evento") Evento evento) {
        eventoService.salvar(evento); 
        return "redirect:/web/eventos"; 
    }

    // 5. PROCESSAR EXCLUSÃO
    @GetMapping("/deletar/{id}")
    public String deletarEvento(@PathVariable Long id) {
        eventoService.deletarPorId(id); 
        return "redirect:/web/eventos"; 
    }
}