package com.demo.crudapp.repository;

import com.demo.crudapp.entity.Empleado;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    List<Empleado> findByNombreContainingAndApellidoContaining(String nombre, String apellido);

    List<Empleado> findByPuesto(String filtro);

    List<Empleado> findAll(Sort sort);

}