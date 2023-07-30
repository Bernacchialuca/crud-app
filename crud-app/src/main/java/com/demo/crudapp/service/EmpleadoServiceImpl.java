package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> getEmpleados() {
        return this.empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getEmpleadoById(Long id) { return this.empleadoRepository.findById(id); }

    @Override
    public void save(Empleado empleado) {
        this.empleadoRepository.save(empleado);
    }

    @Override
    public void delete(Long id) {
        this.empleadoRepository.deleteById(id);
    }

    public List<Empleado> buscarPorNombreYApellido(String nombreApellido) {

        String nombre = "";
        String apellido = "";

        if (nombreApellido != null) {
            String[] parts = nombreApellido.split(" ");
            if (parts.length > 0) {
                nombre = parts[0];
                if (parts.length > 1) {
                    apellido = parts[1];
                }
            }
        }
        return empleadoRepository.findByNombreContainingAndApellidoContaining(nombre, apellido);
    }


}
