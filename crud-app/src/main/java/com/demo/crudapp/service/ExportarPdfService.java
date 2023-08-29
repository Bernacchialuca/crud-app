package com.demo.crudapp.service;

import com.demo.crudapp.entity.Empleado;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public interface ExportarPdfService {

    void exportToPdf(HttpServletResponse response, List<Empleado> allEmpleados) throws IOException;
    PdfPCell createStyledCell(String text, Font font, int horizontalAlignment, Color backgroundColor);
    PdfPCell createStyledCell(String text, Font font, int horizontalAlignment);
}
