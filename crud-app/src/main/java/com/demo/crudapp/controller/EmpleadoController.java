package com.demo.crudapp.controller;

import com.demo.crudapp.entity.Ciudad;
import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.service.CiudadService;
import com.demo.crudapp.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/listado")
    public String verEmpleados(Model model) {

        List<Empleado> listaDeEmpleados = this.empleadoService.getEmpleados();
        model.addAttribute("listaDeEmpleados", listaDeEmpleados);
        model.addAttribute("titulo", "Listado de empleados");
        return "verEmpleados";

        }

    @GetMapping("/crear")
    public String guardarEmpleado(Model model) {

        Empleado empleado = new Empleado();
        List<Ciudad> listaDeCiudades = this.ciudadService.getCiudades();
        model.addAttribute("titulo", "Crear empleado");
        model.addAttribute("empleado", empleado);
        model.addAttribute("listaDeCiudades", listaDeCiudades);
        return "formularioEmpleado";

    }

    @PostMapping("/guardar")
    public String guardarEmpleado(@ModelAttribute Empleado empleado) {
        this.empleadoService.save(empleado);
        return "redirect:/empleados/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id) {

        this.empleadoService.delete(id);
        return "redirect:/empleados/listado";

    }

    @GetMapping("/editar/{id}")
    public String editarempleado(@PathVariable("id") Long idEmpleado, Model model) {

        Optional<Empleado> empleado = this.empleadoService.getEmpleadoById(idEmpleado);
        List<Ciudad> listaDeCiudades = this.ciudadService.getCiudades();

        model.addAttribute("titulo", "Editar Empleado");
        model.addAttribute("empleado",empleado);
        model.addAttribute("listaDeCiudades", listaDeCiudades);

        return "formularioEmpleado";

    }

}
