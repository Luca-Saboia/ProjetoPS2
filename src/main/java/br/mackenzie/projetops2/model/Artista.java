package br.mackenzie.projetops2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String estiloMusical;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    private List<Evento> eventos;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEstiloMusical() { return estiloMusical; }
    public void setEstiloMusical(String estiloMusical) { this.estiloMusical = estiloMusical; }
    public List<Evento> getEventos() { return eventos; }
    public void setEventos(List<Evento> eventos) { this.eventos = eventos; }
}