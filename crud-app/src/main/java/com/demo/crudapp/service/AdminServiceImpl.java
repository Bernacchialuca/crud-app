package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Empleado> getEmpleados() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Empleado> findByIdOptional(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public void save(Empleado empleado) {
        adminRepository.save(empleado);
    }

    @Override
    public void delete(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public Page<Empleado> buscarEmpleados(int page, String nombreApellido, String filtro) {
        Pageable pageable = PageRequest.of(page, 10);
        switch (filtro) {
            case "mayor-salario":
                return adminRepository.findAllByOrderBySalarioDesc(pageable);
            case "menor-salario":
                return adminRepository.findAllByOrderBySalarioAsc(pageable);
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
        return adminRepository.findByNombreContainingAndApellidoContaining(nombre, apellido,pageable);
    }

    @Override
    public Page<Empleado> buscarPorPuesto(String puesto, Pageable pageable) {
        return adminRepository.findByPuesto(puesto, pageable);
    }

    @Override
    public Page<Empleado> getAll(Pageable pageable) {
        return this.adminRepository.findAll(pageable);
    }

    @Override
    public Long countByPuesto(String puesto) {
        return this.adminRepository.countByPuesto(puesto);
    }
    @Override
    public Long countEmpleadosPorCiudad(String nombreCiudad) {
        return this.adminRepository.countEmpleadosPorCiudad(nombreCiudad);
    }

    @Override
    public Long countEmpleadosPorGenero(String genero) {
        return this.adminRepository.countByGenero(genero);
    }

    @Override
    public Long countBySalarioLessThan(int salario) {
        return this.adminRepository.countBySalarioLessThan(salario);
    }

    @Override
    public Long countBySalarioGreaterThan(int salario) {
        return this.adminRepository.countBySalarioGreaterThan(salario);
    }

    @Override
    public Long countBySalarioBetween(int minSalario, int maxSalario) {
        return this.adminRepository.countBySalarioBetween(minSalario,maxSalario);
    }

    @Override
    public Empleado getEmpleadoById(Long idEmpleado) {
        return this.adminRepository.findById(idEmpleado).orElse(null);
    }
}
