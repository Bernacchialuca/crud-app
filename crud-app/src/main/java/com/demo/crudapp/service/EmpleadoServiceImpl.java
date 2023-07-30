package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<Empleado> buscarPorPuesto(String filtro) {
        return empleadoRepository.findByPuesto(filtro);
    }

    @Override
    public List<Empleado> buscarPorSalarioMayor() {
        return empleadoRepository.findAll(Sort.by(Sort.Direction.DESC, "salario"));
    }

    @Override
    public List<Empleado> buscarPorSalarioMenor() {
        return empleadoRepository.findAll(Sort.by(Sort.Direction.ASC, "salario"));
    }

    @Override
    public List<Empleado> buscarEmpleados(String nombreApellido, String filtro) {
        switch (filtro) {
            case "mayor-salario":
                return buscarPorSalarioMayor();
            case "menor-salario":
                return buscarPorSalarioMenor();
            case "Project Manager":
            case "Team Leader":
            case "Frontend Developer":
            case "Backend Developer":
            case "Full Stack Developer":
                return buscarPorPuesto(filtro);
            default:
                return buscarPorNombreYApellido(nombreApellido);
        }
    }


}
