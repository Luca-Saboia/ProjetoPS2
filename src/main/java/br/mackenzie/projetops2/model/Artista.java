package br.mackenzie.projetops2.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeArtistico;

    @Column(nullable = false)
    private String generoMusical;

    //evitar o loop infinito de JSON 
    @JsonIgnore
    @ManyToMany(mappedBy = "artistas", fetch = FetchType.LAZY)
    private List<Evento> eventos = new ArrayList<>();

    // Construtores
    public Artista() {}

    public Artista(String nomeArtistico, String generoMusical) {
        this.nomeArtistico = nomeArtistico;
        this.generoMusical = generoMusical;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArtistico() {
        return nomeArtistico;
    }

    public void setNomeArtistico(String nomeArtistico) {
        this.nomeArtistico = nomeArtistico;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}