package com.demo.crudapp.entity;

import com.demo.crudapp.validation.NotZero;
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

    @NotEmpty(message = "El campo nombre no puede estar vacío")
    private String nombre;

    @NotEmpty(message = "El campo apellido no puede estar vacío")
    private String apellido;

    @NotNull
    @Size(min = 5, max=8, message = "El campo telefono debe tener entre 5 y 8 números")
    private String telefono;

    @NotNull(message = "El campo DNI debe estar lleno")
    private Long dni;

    @NotZero(message = "El valor de sexo no puede estar vacío")
    private String sexo;

    @NotEmpty(message = "El campo email no puede estar vacío")
    @Email(message = "Ingresa un formato valido de email")
    private String email;

    @NotNull(message = "El campo salario no puede estar vacío")
    private Integer salario;

    @NotZero(message = "El valor de puesto no puede estar vacío")
    private String puesto;

    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;


}
