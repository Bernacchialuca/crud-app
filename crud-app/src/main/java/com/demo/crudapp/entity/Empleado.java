package com.demo.crudapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="Empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String nombre;
    @NotEmpty(message = "El campo apellido no puede estar vacio")
    private String apellido;
    @NotNull
    @Size(min = 5, max=8, message = "El campo telefono debe tener entre 5 y 8 numeros")
    private String telefono;
    @NotEmpty(message = "El campo email no puede estar vacio")
    @Email(message = "Ingresa un formato valido de email")
    private String email;
    @NotNull(message = "El campo salario no puede estar vacio")
    private Integer salario;
    @NotEmpty(message = "El campo puesto no puede estar vacio")
    private String puesto;
    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;


}
