package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AdminService {

    List<Empleado> getEmpleados();

    Optional<Empleado> findByIdOptional(Long id);

    void save(Empleado empleado);

    void delete(Long id);

    Page<Empleado> buscarEmpleados(int page, String nombreApellido, String filtro);

    Page<Empleado> buscarPorNombreYApellido(String nombreApellido, Pageable pageable);

    Page<Empleado> buscarPorPuesto(String puesto, Pageable pageable);

    Page<Empleado> getAll(Pageable pageable);

    Long countByPuesto(String puesto);

    Long countEmpleadosPorCiudad(String nombreCiudad);

    Long countEmpleadosPorGenero(String genero);

    Long countBySalarioLessThan(int salario);
    Long countBySalarioGreaterThan(int salario);
    Long countBySalarioBetween(int minSalario, int maxSalario);

    Empleado getEmpleadoById(Long idEmpleado);

    Map<String, Long> obtenerEmpleadosPorCiudad();

    Map<String, Long> obtenerEmpleadosPorPuesto();

    Map<String, Long> obtenerEmpleadosPorGenero();

    Map<String, Long> obtenerEmpleadosPorSalario();
}

