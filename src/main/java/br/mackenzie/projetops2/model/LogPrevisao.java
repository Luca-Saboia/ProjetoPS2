package br.mackenzie.projetops2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "logs_previsao")
public class LogPrevisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperatura;
    private String condicaoClimatica;
    private Integer umidade;
    private LocalDateTime dataConsulta;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    @JsonIgnore
    private Evento evento;

    public LogPrevisao() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }
    public String getCondicaoClimatica() { return condicaoClimatica; }
    public void setCondicaoClimatica(String condicaoClimatica) { this.condicaoClimatica = condicaoClimatica; }
    public Integer getUmidade() { return umidade; }
    public void setUmidade(Integer umidade) { this.umidade = umidade; }
    public LocalDateTime getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDateTime dataConsulta) { this.dataConsulta = dataConsulta; }
    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }
}