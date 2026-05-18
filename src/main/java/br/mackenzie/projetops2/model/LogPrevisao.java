package br.mackenzie.projetops2.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_previsoes")
public class LogPrevisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperatura;
    private String descricaoClima; // Ex: "Chuva", "Ensolarado"
    private Integer umidade;
    private LocalDateTime dataConsulta;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getTemperatura() { return temperatura; }
    public void setTemperatura(Double temperatura) { this.temperatura = temperatura; }
    public String getDescricaoClima() { return descricaoClima; }
    public void setDescricaoClima(String descricaoClima) { this.descricaoClima = descricaoClima; }
    public Integer getUmidade() { return umidade; }
    public void setUmidade(Integer umidade) { this.umidade = umidade; }
    public LocalDateTime getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(LocalDateTime dataConsulta) { this.dataConsulta = dataConsulta; }
}