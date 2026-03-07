package com.TechosYEstructuras.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_PROYECTO_TB")
public class Proyectos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROYECTO")
    private Integer idProyecto;

    @NotBlank
    @Size(max = 100)
    @Column(name = "NOMBRE_PROYECTO")
    private String nombreProyecto;

    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Size(max = 150)
    @Column(name = "UBICACION")
    private String ubicacion;

    @Size(max = 20)
    @Column(name = "ESTADO")
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENTE")
    private Clientes cliente;
    
}