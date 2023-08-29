package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Map<String, Long> obtenerEmpleadosPorPuesto() {
        Map<String, Long> empleadosPorPuesto = new TreeMap<>();

        empleadosPorPuesto.put("Frontend Developers", countByPuesto("Frontend Developer"));
        empleadosPorPuesto.put("Backend Developers", countByPuesto("Backend Developer"));
        empleadosPorPuesto.put("Full Stack Developers", countByPuesto("Full Stack Developer"));
        empleadosPorPuesto.put("Team Leaders", countByPuesto("Team Leader"));
        empleadosPorPuesto.put("Project Managers", countByPuesto("Project Manager"));

        return empleadosPorPuesto;
    }

    public Map<String, Long> obtenerEmpleadosPorCiudad() {
        Map<String, Long> empleadosPorCiudad = new TreeMap<>();

        empleadosPorCiudad.put("Madrid", countEmpleadosPorCiudad("Madrid"));
        empleadosPorCiudad.put("Los Angeles", countEmpleadosPorCiudad("Los Angeles"));
        empleadosPorCiudad.put("Paris", countEmpleadosPorCiudad("Paris"));
        empleadosPorCiudad.put("India", countEmpleadosPorCiudad("India"));
        empleadosPorCiudad.put("Mexico", countEmpleadosPorCiudad("Mexico"));
        empleadosPorCiudad.put("Buenos Aires", countEmpleadosPorCiudad("Buenos Aires"));
        empleadosPorCiudad.put("Tokio", countEmpleadosPorCiudad("Tokio"));
        empleadosPorCiudad.put("Londres", countEmpleadosPorCiudad("Londres"));

        return empleadosPorCiudad;
    }

    public Map<String, Long> obtenerEmpleadosPorGenero() {
        Map<String, Long> empleadosPorGenero = new TreeMap<>();

        empleadosPorGenero.put("Masculino", countEmpleadosPorGenero("Masculino"));
        empleadosPorGenero.put("Femenino", countEmpleadosPorGenero("Femenino"));
        empleadosPorGenero.put("Otro", countEmpleadosPorGenero("Otro"));

        return empleadosPorGenero;
    }

    public Map<String, Long> obtenerEmpleadosPorSalario() {
        Map<String, Long> empleadosPorSalario = new LinkedHashMap<>();

        empleadosPorSalario.put("Menor a 2000", countBySalarioLessThan(2000));
        empleadosPorSalario.put("Entre 2000 a 4000", countBySalarioBetween(2000, 4000));
        empleadosPorSalario.put("Entre 4000 a 6000", countBySalarioBetween(4000, 6000));
        empleadosPorSalario.put("Entre 6000 a 8000", countBySalarioBetween(6000, 8000));
        empleadosPorSalario.put("Entre 8000 a 10000", countBySalarioBetween(8000, 10000));
        empleadosPorSalario.put("Mayor a 10000", countBySalarioGreaterThan(10000));

        return empleadosPorSalario;
    }

    @Override
    public Page<Empleado> buscarEmpleados(int page, String nombreApellido, String filtro) {
        Pageable pageable = PageRequest.of(page, 10);
        return switch (filtro) {
            case "mayor-salario" -> adminRepository.findAllByOrderBySalarioDesc(pageable);
            case "menor-salario" -> adminRepository.findAllByOrderBySalarioAsc(pageable);
            case "Project Manager", "Team Leader", "Frontend Developer", "Backend Developer", "Full Stack Developer" ->
                    buscarPorPuesto(filtro, pageable);
            default -> buscarPorNombreYApellido(nombreApellido, pageable);
        };
    }

    @Override
    public Page<Empleado> buscarPorNombreYApellido(String nombreApellido, Pageable pageable) {
        String nombre = "";
        String apellido = "";

        if (nombreApellido != null) {
            String[] parts = nombreApellido.split(" ");
            if (parts.length > 0) {
                apellido = parts[parts.length - 1]; // Last part is the last name
                if (parts.length > 1) {
                    nombre = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 1));
                }
            }
        }
        return adminRepository.findByNombreContainingAndApellidoContaining(nombre, apellido, pageable);
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
