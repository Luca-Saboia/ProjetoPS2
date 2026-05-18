package br.mackenzie.projetops2.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataEvento;

    @Column(nullable = false)
    private Double preco;

    private String status; // Ex: "Agendado", "Alerta de Chuva", "Confirmado"

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario organizador;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista actor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "log_previsao_id")
    private LogPrevisao previsaoDoTempo;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDataEvento() { return dataEvento; }
    public void setDataEvento(LocalDate dataEvento) { this.dataEvento = dataEvento; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Usuario getOrganizador() { return organizador; }
    public void setOrganizador(Usuario organizador) { this.organizador = organizador; }
    public Artista getArtista() { return actor; }
    public void setArtista(Artista artista) { this.actor = artista; }
    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }
    public LogPrevisao getPrevisaoDoTempo() { return previsaoDoTempo; }
    public void setPrevisaoDoTempo(LogPrevisao previsaoDoTempo) { this.previsaoDoTempo = previsaoDoTempo; }
}