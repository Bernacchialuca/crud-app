package com.demo.crudapp.entity;

import com.demo.crudapp.validation.NotZero;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="Tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "La descripcion de la tarea no puede estar vacia")
    private String descripcion;
    @NotNull(message = "La fecha limite de la tarea no puede estar vacia")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @FutureOrPresent(message = "La fecha limite debe ser presente o futura")
    private LocalDate fechaLimite;
    private LocalDate fechaDeAsignacion;
    @NotZero(message = "La prioridad de la tarea no puede estar vacia")
    private String prioridad;
    private Boolean completada;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;

}
