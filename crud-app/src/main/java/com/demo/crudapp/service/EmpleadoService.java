package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {

    List<Empleado> getEmpleados();

    Optional<Empleado> getEmpleadoById(Long id);

    void save(Empleado empleado);
    void delete(Long id);
    List<Empleado> buscarPorNombreYApellido(String nombreApellido);

    List<Empleado> buscarPorPuesto(String filtro);

    List<Empleado> buscarPorSalarioMayor();

    List<Empleado> buscarPorSalarioMenor();
}
