package br.mackenzie.projetops2.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "localizacoes")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city; // Mapeado de acordo com a API OpenWeather

    @Column(nullable = false)
    private String estado;

    private Double latitude;
    private Double longitude;

    @JsonIgnore
    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL)
    private List<Evento> eventos;

    public Localizacao() {}

    public Localizacao(String city, String estado, Double latitude, Double longitude) {
        this.city = city;
        this.estado = estado;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public List<Evento> getEventos() { return eventos; }
    public void setEventos(List<Evento> eventos) { this.eventos = eventos; }
}