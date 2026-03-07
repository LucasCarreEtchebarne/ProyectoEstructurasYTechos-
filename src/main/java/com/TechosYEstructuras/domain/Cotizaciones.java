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
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_COTIZACION_TB")
public class Cotizaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COTIZACION")
    private Integer idCotizacion;

    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "MONTO_TOTAL")
    private BigDecimal montoTotal;

    @Size(max = 20)
    @Column(name = "ESTADO")
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CLIENTE")
    private Clientes cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PROYECTO")
    private Proyectos proyecto;
    
}