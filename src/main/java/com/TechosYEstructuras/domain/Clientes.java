package com.TechosYEstructuras.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "FIDE_CLIENTE_TB")
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;

    @NotBlank
    @Size(max = 20)
    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TELEFONO")
    private String telefono;

    @Size(max = 120)
    @Column(name = "CORREO")
    private String correo;

    @Size(max = 200)
    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "ACTIVO")
    private Boolean activo;
    
}
