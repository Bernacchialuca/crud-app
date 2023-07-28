package com.demo.crudapp.entity;

import jakarta.persistence.*;
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
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Integer salario;
    private String puesto;
    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    private Ciudad ciudad;


}
