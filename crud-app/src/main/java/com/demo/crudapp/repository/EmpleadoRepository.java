package com.demo.crudapp.repository;

import com.demo.crudapp.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    void deleteById(Long id);
}