package br.mackenzie.projetops2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHora;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "evento_artista",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "artista_id")
    )
    private List<Artista> artistas = new ArrayList<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogPrevisao> logsPrevisao = new ArrayList<>();

    // Construtores
    public Evento() {}

    public Evento(String nome, LocalDateTime dataHora, Localizacao localizacao) {
        this.nome = nome;
        this.dataHora = dataHora;
        this.localizacao = localizacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public List<Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public List<LogPrevisao> getLogsPrevisao() {
        return logsPrevisao;
    }

    public void setLogsPrevisao(List<LogPrevisao> logsPrevisao) {
        this.logsPrevisao = logsPrevisao;
    }
}