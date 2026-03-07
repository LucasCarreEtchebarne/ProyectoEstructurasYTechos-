package com.TechosYEstructuras.service;

import com.TechosYEstructuras.domain.Cotizaciones;
import com.TechosYEstructuras.repository.CotizacionesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CotizacionesService {

    private final CotizacionesRepository cotizacionesRepository;

    public CotizacionesService(CotizacionesRepository cotizacionesRepository) {
        this.cotizacionesRepository = cotizacionesRepository;
    }

    @Transactional(readOnly = true)
    public List<Cotizaciones> getCotizaciones() {
        return cotizacionesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cotizaciones> getCotizacion(Integer idCotizacion) {
        return cotizacionesRepository.findById(idCotizacion);
    }

    @Transactional
    public void save(Cotizaciones cotizacion) {
        cotizacionesRepository.save(cotizacion);
    }

    @Transactional
    public void delete(Integer idCotizacion) {
        cotizacionesRepository.deleteById(idCotizacion);
    }
}