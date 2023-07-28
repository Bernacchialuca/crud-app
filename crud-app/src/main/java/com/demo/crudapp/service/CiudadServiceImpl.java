package com.demo.crudapp.service;

import com.demo.crudapp.entity.Ciudad;
import com.demo.crudapp.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public List<Ciudad> getCiudades() {
        return this.ciudadRepository.findAll();
    }
}
