package com.TechosYEstructuras.service;

import com.TechosYEstructuras.domain.Proyectos;
import com.TechosYEstructuras.repository.ProyectosRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProyectosService {

    private final ProyectosRepository proyectosRepository;

    public ProyectosService(ProyectosRepository proyectosRepository) {
        this.proyectosRepository = proyectosRepository;
    }

    @Transactional(readOnly = true)
    public List<Proyectos> getProyectos() {
        return proyectosRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Proyectos> getProyecto(Integer idProyecto) {
        return proyectosRepository.findById(idProyecto);
    }

    @Transactional
    public void save(Proyectos proyecto) {
        proyectosRepository.save(proyecto);
    }

    @Transactional
    public void delete(Integer idProyecto) {
        proyectosRepository.deleteById(idProyecto);
    }
}