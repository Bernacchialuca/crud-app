package com.demo.crudapp.controller;

import com.demo.crudapp.entity.Ciudad;
import com.demo.crudapp.entity.Cliente;
import com.demo.crudapp.service.CiudadService;
import com.demo.crudapp.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/listado")
    public String verClientes(Model model) {

        List<Cliente> listaDeClientes = this.clienteService.getClientes();
        model.addAttribute("listaDeClientes", listaDeClientes);
        model.addAttribute("titulo", "Listado de Clientes");
        return "verClientes";

        }

    @GetMapping("/crear")
    public String guardarCliente(Model model) {

        Cliente cliente = new Cliente();
        List<Ciudad> listaDeCiudades = this.ciudadService.getCiudades();
        model.addAttribute("titulo", "Crear cliente");
        model.addAttribute("cliente", cliente);
        model.addAttribute("listaDeCiudades", listaDeCiudades);
        return "formularioCliente";

    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        this.clienteService.save(cliente);
        return "redirect:/clientes/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {

        this.clienteService.delete(id);
        return "redirect:/clientes/listado";

    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable("id") Long idCliente, Model model) {

        Optional<Cliente> cliente = this.clienteService.getClienteById(idCliente);
        List<Ciudad> listaDeCiudades = this.ciudadService.getCiudades();

        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("cliente",cliente);
        model.addAttribute("listaDeCiudades", listaDeCiudades);

        return "formularioCliente";

    }

}
