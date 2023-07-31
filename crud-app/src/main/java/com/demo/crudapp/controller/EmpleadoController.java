package com.demo.crudapp.controller;

import com.demo.crudapp.entity.Ciudad;
import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.service.CiudadService;
import com.demo.crudapp.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/listado")
    public String verEmpleados(@RequestParam Map<String, Object> params, Model model) {

        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Empleado> pagePersona = this.empleadoService.getAll(pageRequest);

        int totalPage = pagePersona.getTotalPages();

        if(totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("titulo", "Sistema de gestion de empleados");
        model.addAttribute("listaDeEmpleados", pagePersona.getContent());
        model.addAttribute("prevPage", page);
        model.addAttribute("currentPage", page + 1);
        model.addAttribute("nextPage", page + 2);
        model.addAttribute("lastPage", totalPage);

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
    public String guardarEmpleado(@Valid @ModelAttribute Empleado empleado, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        List<Ciudad> listaDeCiudades = this.ciudadService.getCiudades();
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Crear empleado");
            model.addAttribute("empleado", empleado);
            model.addAttribute("listaDeCiudades", listaDeCiudades);
            return "formularioEmpleado";
        }

        String action = (empleado.getId() == null) ? "creado" : "editado";
        String mensaje = "El empleado " + empleado.getNombre() + " " + empleado.getApellido() +  " fue " + action + " correctamente";
        redirectAttributes.addFlashAttribute("success", mensaje);

        this.empleadoService.save(empleado);
        return "redirect:/empleados/listado";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        Optional<Empleado> empleado = this.empleadoService.getEmpleadoById(id);
        this.empleadoService.delete(id);
        String mensaje = "El empleado " + empleado.get().getNombre() + " " + empleado.get().getApellido() + " fue eliminado correctamente";

        redirectAttributes.addFlashAttribute("eliminado", mensaje);
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

    @GetMapping("/search")
    public String buscarEmpleados(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(required = false) String nombreApellido,
                                  @RequestParam(required = false) String filtro,
                                  Model model) {

        Page<Empleado> pagePersona = empleadoService.buscarEmpleados(page, nombreApellido, filtro);

        int totalPage = pagePersona.getTotalPages();

        if (totalPage > 0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }

        model.addAttribute("titulo", "Sistema de gestion de empleados");
        model.addAttribute("listaDeEmpleados", pagePersona.getContent());
        model.addAttribute("prevPage", page);
        model.addAttribute("currentPage", page + 1);
        model.addAttribute("nextPage", page + 2);
        model.addAttribute("lastPage", totalPage);

        return "verEmpleados";
    }

}
