package com.demo.crudapp.service;

import com.demo.crudapp.entity.Cliente;
import com.demo.crudapp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getClientes() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long id) { return this.clienteRepository.findById(id); }

    @Override
    public void save(Cliente cliente) {
        this.clienteRepository.save(cliente);
    }

    @Override
    public void delete(Long id) {
        this.clienteRepository.deleteById(id);
    }


}
