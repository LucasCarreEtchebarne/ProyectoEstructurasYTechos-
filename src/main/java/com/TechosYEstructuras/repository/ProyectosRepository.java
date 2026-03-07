package com.TechosYEstructuras.repository;

import com.TechosYEstructuras.domain.Proyectos;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectosRepository extends JpaRepository<Proyectos, Integer> {

    List<Proyectos> findByEstado(String estado);

}