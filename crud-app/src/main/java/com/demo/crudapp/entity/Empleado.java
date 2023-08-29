package com.demo.crudapp.entity;

import com.demo.crudapp.validation.NotZero;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="Empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Por favor, ingresa un nombre")
    private String nombre;

    @NotEmpty(message = "Por favor, ingresa un apellido")
    private String apellido;

    @NotNull
    @Size(min = 5, max=12, message = "El campo telefono debe tener entre 5 y 12 números")
    private String telefono;

    @NotNull(message = "Por favor, ingresa un DNI")
    private Long dni;

    @NotZero(message = "Por favor, selecciona el sexo")
    private String genero;

    @NotEmpty(message = "Por favor, ingresa un email")
    @Email(message = "Por favor, ingresa un formato valido de email")
    private String email;

    @NotNull(message = "Por favor, ingresa el salario")
    private Integer salario;

    @NotZero(message = "Por favor, selecciona el puesto")
    private String puesto;

    @ManyToOne
    @NotNull(message = "Por favor, selecciona una ciudad")
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;


}
