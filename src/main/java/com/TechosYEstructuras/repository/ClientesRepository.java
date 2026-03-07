package com.TechosYEstructuras.repository;

import com.TechosYEstructuras.domain.Clientes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes, Integer> {
    List<Clientes> findByActivoTrue();
}