package com.TechosYEstructuras.service;

import com.TechosYEstructuras.domain.Clientes;
import com.TechosYEstructuras.repository.ClientesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Transactional(readOnly = true)
    public List<Clientes> getClientes() {
        return clientesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Clientes> getCliente(Integer idCliente) {
        return clientesRepository.findById(idCliente);
    }

    @Transactional
    public void save(Clientes cliente) {
        clientesRepository.save(cliente);
    }

    @Transactional
    public void delete(Integer idCliente) {
        clientesRepository.deleteById(idCliente);
    }
}