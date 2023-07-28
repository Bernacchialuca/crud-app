package com.demo.crudapp.service;

import com.demo.crudapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> getClientes();

    Optional<Cliente> getClienteById(Long id);

    void save(Cliente cliente);
    void delete(Long id);

}
