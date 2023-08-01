package com.demo.crudapp.controller;

import com.demo.crudapp.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class HomeController {

    @Autowired
    private EmpleadoService empleadosService;

    @GetMapping({"/", "/home"})
    public String index(Model model) {

        Map<String, Long> empleadosPorPuesto = new TreeMap<>();

        Long cantidadDeFrontendDevs = this.empleadosService.countByPuesto("Frontend Developer");
        Long cantidadDeBackendDevs = this.empleadosService.countByPuesto("Backend Developer");
        Long cantidadDeFullStackDevs = this.empleadosService.countByPuesto("Full Stack Developer");
        Long cantidadDeTeamLeaders = this.empleadosService.countByPuesto("Team Leader");
        Long cantidadDeProjectManagers = this.empleadosService.countByPuesto("Project Manager");

        empleadosPorPuesto.put("Frontend Developers", cantidadDeFrontendDevs);
        empleadosPorPuesto.put("Backend Developers", cantidadDeBackendDevs);
        empleadosPorPuesto.put("Full Stack Developers", cantidadDeFullStackDevs);
        empleadosPorPuesto.put("Team Leaders", cantidadDeTeamLeaders);
        empleadosPorPuesto.put("Project Managers", cantidadDeProjectManagers);

        Long cantidadDeEmpleadosEnMadrid = this.empleadosService.countEmpleadosPorCiudad("Madrid");
        Long cantidadDeEmpleadosEnLosAngeles = this.empleadosService.countEmpleadosPorCiudad("Los Angeles");
        Long cantidadDeEmpleadosEnParis = this.empleadosService.countEmpleadosPorCiudad("Paris");
        Long cantidadDeEmpleadosEnIndia = this.empleadosService.countEmpleadosPorCiudad("India");
        Long cantidadDeEmpleadosEnMexico = this.empleadosService.countEmpleadosPorCiudad("Mexico");
        Long cantidadDeEmpleadosEnBuenosAires = this.empleadosService.countEmpleadosPorCiudad("Buenos Aires");
        Long cantidadDeEmpleadosEnTokio = this.empleadosService.countEmpleadosPorCiudad("Tokio");
        Long cantidadDeEmpleadosEnLondres = this.empleadosService.countEmpleadosPorCiudad("Londres");

        Map<String, Long> empleadosPorCiudad = new TreeMap<>();

        empleadosPorCiudad.put("Madrid", cantidadDeEmpleadosEnMadrid);
        empleadosPorCiudad.put("Los Angeles", cantidadDeEmpleadosEnLosAngeles);
        empleadosPorCiudad.put("Paris", cantidadDeEmpleadosEnParis);
        empleadosPorCiudad.put("India", cantidadDeEmpleadosEnIndia);
        empleadosPorCiudad.put("Mexico", cantidadDeEmpleadosEnMexico);
        empleadosPorCiudad.put("Buenos Aires", cantidadDeEmpleadosEnBuenosAires);
        empleadosPorCiudad.put("Tokio", cantidadDeEmpleadosEnTokio);
        empleadosPorCiudad.put("Londres", cantidadDeEmpleadosEnLondres);

        Map<String,Long> empleadosPorGenero = new TreeMap<>();

        Long cantidadDeEmpleadosMasculinos = this.empleadosService.countEmpleadosPorGenero("Masculino");
        Long cantidadDeEmpleadosFemeninos = this.empleadosService.countEmpleadosPorGenero("Femenino");
        Long cantidadDeEmpleadosOtro = this.empleadosService.countEmpleadosPorGenero("Otro");

        empleadosPorGenero.put("Masculino", cantidadDeEmpleadosMasculinos);
        empleadosPorGenero.put("Femenino", cantidadDeEmpleadosFemeninos);
        empleadosPorGenero.put("Otro", cantidadDeEmpleadosOtro);

        Map<String, Long> empleadosPorSalario = new LinkedHashMap<>();

        Long cantidadDeEmpleadosConSueldomenorA2000 = this.empleadosService.countBySalarioLessThan(2000);
        Long cantidadDeEmpleadosConSueldoentre2000Y4000 = this.empleadosService.countBySalarioBetween(2000, 4000);
        Long cantidadDeEmpleadosConSueldoentre4000Y6000 = this.empleadosService.countBySalarioBetween(4000, 6000);
        Long cantidadDeEmpleadosConSueldoentre6000Y8000 = this.empleadosService.countBySalarioBetween(6000, 8000);
        Long cantidadDeEmpleadosConSueldoentre8000Y10000 = this.empleadosService.countBySalarioBetween(8000, 10000);
        Long cantidadDeEmpleadosConSueldoMayorA10000 = this.empleadosService.countBySalarioGreaterThan(10000);

        empleadosPorSalario.put("Menor a 2000", cantidadDeEmpleadosConSueldomenorA2000);
        empleadosPorSalario.put("Entre 2000 a 4000", cantidadDeEmpleadosConSueldoentre2000Y4000);
        empleadosPorSalario.put("Entre 4000 a 6000", cantidadDeEmpleadosConSueldoentre4000Y6000);
        empleadosPorSalario.put("Entre 6000 a 8000", cantidadDeEmpleadosConSueldoentre6000Y8000);
        empleadosPorSalario.put("Entre 8000 a 10000", cantidadDeEmpleadosConSueldoentre8000Y10000);
        empleadosPorSalario.put("Mayor a 10000", cantidadDeEmpleadosConSueldoMayorA10000);

        model.addAttribute("titulo", "Home");
        model.addAttribute("empleadosPorCiudadData", empleadosPorCiudad);
        model.addAttribute("empleadosPorPuestoData", empleadosPorPuesto);
        model.addAttribute("empleadosPorGeneroData", empleadosPorGenero);
        model.addAttribute("empleadosPorSalario", empleadosPorSalario);

        return "home";

    }
}
