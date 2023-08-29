package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@Service
public class ExportarPdfServiceImpl implements ExportarPdfService{
    @Override
    public void exportToPdf(HttpServletResponse response, List<Empleado> allEmpleados) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"empleados.pdf\"");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        PdfPTable table = new PdfPTable(8);

        table.setWidthPercentage(110);
        float[] columnWidths = { 1.8f, 1.8f, 2f, 2f, 5f, 1.5f, 2f, 1.5f };
        table.setWidths(columnWidths);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Color.white);

        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10);

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

        allEmpleados.forEach(empleado -> {
            table.addCell(createStyledCell(Long.toString(empleado.getDni()), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getNombre(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getApellido(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getTelefono(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getEmail(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell("$" + empleado.getSalario(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getPuesto(), dataFont, Element.ALIGN_CENTER));
            table.addCell(createStyledCell(empleado.getCiudad().getCiudad(), dataFont, Element.ALIGN_CENTER));
        });

        document.add(table);
        document.close();
    }

    @Override
    public PdfPCell createStyledCell(String text, Font font, int horizontalAlignment, Color backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(horizontalAlignment);
        cell.setBackgroundColor(backgroundColor);
        cell.setPadding(5);
        return cell;
    }

    @Override
    public PdfPCell createStyledCell(String text, Font font, int horizontalAlignment) {
        return createStyledCell(text, font, horizontalAlignment, Color.WHITE);
    }
}
