package com.demo.crudapp.repository;

import com.demo.crudapp.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Page<Empleado> findByNombreContainingAndApellidoContaining(String nombre, String apellido, Pageable pageable);

    Page<Empleado> findByPuesto(String filtro, Pageable pageable);

    Page<Empleado> findAllByOrderBySalarioDesc(Pageable pageable);

    Page<Empleado> findAllByOrderBySalarioAsc(Pageable pageable);

}