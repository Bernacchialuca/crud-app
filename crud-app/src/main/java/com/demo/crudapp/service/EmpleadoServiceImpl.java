package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public List<Empleado> getEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> getEmpleadoById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public void save(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    public void delete(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public Page<Empleado> buscarEmpleados(int page, String nombreApellido, String filtro) {
        Pageable pageable = PageRequest.of(page, 10);
        switch (filtro) {
            case "mayor-salario":
                return empleadoRepository.findAllByOrderBySalarioDesc(pageable);
            case "menor-salario":
                return empleadoRepository.findAllByOrderBySalarioAsc(pageable);
            case "Project Manager":
            case "Team Leader":
            case "Frontend Developer":
            case "Backend Developer":
            case "Full Stack Developer":
                return buscarPorPuesto(filtro, pageable);
            default:
                return buscarPorNombreYApellido(nombreApellido, pageable);
        }
    }

    @Override
    public Page<Empleado> buscarPorNombreYApellido(String nombreApellido, Pageable pageable) {
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
        return empleadoRepository.findByNombreContainingAndApellidoContaining(nombre, apellido,pageable);
    }

    @Override
    public Page<Empleado> buscarPorPuesto(String puesto, Pageable pageable) {
        return empleadoRepository.findByPuesto(puesto, pageable);
    }

    @Override
    public Page<Empleado> getAll(Pageable pageable) {
        return this.empleadoRepository.findAll(pageable);
    }

    @Override
    public Long countByPuesto(String puesto) {
        return this.empleadoRepository.countByPuesto(puesto);
    }
    @Override
    public Long countEmpleadosPorCiudad(String nombreCiudad) {
        return this.empleadoRepository.countEmpleadosPorCiudad(nombreCiudad);
    }

    @Override
    public Long countEmpleadosPorGenero(String genero) {
        return this.empleadoRepository.countByGenero(genero);
    }

    @Override
    public Long countBySalarioLessThan(int salario) {
        return this.empleadoRepository.countBySalarioLessThan(salario);
    }

    @Override
    public Long countBySalarioGreaterThan(int salario) {
        return this.empleadoRepository.countBySalarioGreaterThan(salario);
    }

    @Override
    public Long countBySalarioBetween(int minSalario, int maxSalario) {
        return this.empleadoRepository.countBySalarioBetween(minSalario,maxSalario);
    }
}
