package com.demo.crudapp.repository;

import com.demo.crudapp.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Empleado, Long> {


    Page<Empleado> findByPuesto(String filtro, Pageable pageable);

    Page<Empleado> findAllByOrderBySalarioDesc(Pageable pageable);

    Page<Empleado> findAllByOrderBySalarioAsc(Pageable pageable);

    Long countByPuesto(String puesto);

    @Query("SELECT COUNT(e) FROM Empleado e WHERE e.ciudad.ciudad = :nombreCiudad")
    Long countEmpleadosPorCiudad(@Param("nombreCiudad") String nombreCiudad);

    Long countByGenero(String genero);

    Long countBySalarioLessThan(int salario);

    Long countBySalarioGreaterThan(int salario);
    Long countBySalarioBetween(int minSalario, int maxSalario);

    Page<Empleado> findByNombreContainingOrApellidoContaining(String nombreApellido, String nombreApellido1, Pageable pageable);

    Page<Empleado> findByNombreContainingAndApellidoContaining(String nombre, String apellido, Pageable pageable);
}