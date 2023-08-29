package com.demo.crudapp.controller;

import com.demo.crudapp.service.AdminService;
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
    private AdminService adminService;

    @GetMapping({"/", "/home"})
    public String index(Model model) {

        Map<String, Long> empleadosPorCiudadData = this.adminService.obtenerEmpleadosPorCiudad();
        Map<String, Long> empleadosPorPuestoData = this.adminService.obtenerEmpleadosPorPuesto();
        Map<String, Long> empleadosPorGeneroData = this.adminService.obtenerEmpleadosPorGenero();
        Map<String, Long> empleadosPorSalario = this.adminService.obtenerEmpleadosPorSalario();

        model.addAttribute("titulo", "Home");
        model.addAttribute("empleadosPorCiudadData", empleadosPorCiudadData);
        model.addAttribute("empleadosPorPuestoData", empleadosPorPuestoData);
        model.addAttribute("empleadosPorGeneroData", empleadosPorGeneroData);
        model.addAttribute("empleadosPorSalario", empleadosPorSalario);

        return "home";
    }
}
