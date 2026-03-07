package com.TechosYEstructuras.repository;

import com.TechosYEstructuras.domain.Cotizaciones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionesRepository extends JpaRepository<Cotizaciones, Integer> {
    List<Cotizaciones> findByEstado(String estado);
}