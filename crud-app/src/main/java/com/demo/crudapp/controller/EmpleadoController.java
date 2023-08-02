package com.demo.crudapp.controller;

import com.demo.crudapp.entity.Ciudad;
import com.demo.crudapp.entity.Empleado;
import com.demo.crudapp.service.CiudadService;
import com.demo.crudapp.service.EmpleadoService;
import com.itextpdf.text.BaseColor;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.*;
import java.io.IOException;
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

        return "fragments/tablaEmpleadosFiltrados";
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException, DocumentException {
        // Retrieve all employees from the service
        List<Empleado> allEmpleados = empleadoService.getEmpleados(); // Assuming you have a method like this in the service

        // Set the response content type
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"empleados.pdf\"");

        // Create a new PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Create a table for the employee data
        PdfPTable table = new PdfPTable(8); // 8 columns

        // Set the width percentage of the table (80% in this example)
        table.setWidthPercentage(110);
        float[] columnWidths = { 1.8f, 1.8f, 2f, 2f, 5f, 1.5f, 2f, 1.5f };
        table.setWidths(columnWidths);

        // Define a font for header cells
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.white);

        // Define a font for data cells
        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

        // Add headers to the table with color, bold, and centered
        PdfPCell headerCell;
        headerCell = createStyledCell("DNI", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        headerCell = createStyledCell("Nombre", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        headerCell = createStyledCell("Apellido", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        headerCell = createStyledCell("Teléfono", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        headerCell = createStyledCell("Email", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        headerCell = createStyledCell("Salario", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        headerCell = createStyledCell("Puesto", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        headerCell = createStyledCell("Ciudad", headerFont, Element.ALIGN_CENTER, Color.BLACK);
        table.addCell(headerCell);

        // Add the employee data to the table with centered alignment and font size of 10
        for (Empleado empleado : allEmpleados) {
            table.addCell(createStyledCell(Long.toString(empleado.getDni()), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getNombre(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getApellido(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getTelefono(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getEmail(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell("$" + empleado.getSalario(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getPuesto(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getCiudad().getCiudad(), dataFont, Element.ALIGN_CENTER));
        }

        // Add the table to the document
        document.add(table);

        document.close();
    }

    // Helper method to create styled cells
    private PdfPCell createStyledCell(String text, Font font, int horizontalAlignment, Color backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setBackgroundColor(backgroundColor);
        cell.setPadding(5);
        return cell;
    }

    // Helper method to create styled cells without background color
    private PdfPCell createStyledCell(String text, Font font, int horizontalAlignment) {
        return createStyledCell(text, font, horizontalAlignment, Color.WHITE);
    }



}
