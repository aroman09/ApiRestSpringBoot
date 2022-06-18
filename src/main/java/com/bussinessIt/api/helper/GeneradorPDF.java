package com.bussinessIt.api.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneradorPDF {
    public static ByteArrayInputStream PDFReporteCliente(List<Map<String, String>> customers) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);
            document.open();

            // Se agrega el texto al archivo ->
            Font font = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK);
            Paragraph para = new Paragraph("Reporte de Movimientos", font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100);
            // Add PDF Table Header ->
            Stream.of("Fecha", "Cliente", "Numero Cuenta", "Tipo", "Saldo Inicial", "Estado", "Movimiento", "Saldo DIsponible")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        headFont.setSize(10);
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(headerTitle, headFont));
                        table.addCell(header);
                    });

            for (Map<String, String> customer : customers) {

                PdfPCell fecha = new PdfPCell(new Phrase(customer.get("Fecha").substring(0, 10)));
                fecha.setPaddingLeft(4);
                fecha.setVerticalAlignment(Element.ALIGN_MIDDLE);
                fecha.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(fecha);

                PdfPCell cliente = new PdfPCell(new Phrase(customer.get("Cliente")));
                cliente.setPaddingLeft(4);
                cliente.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cliente.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cliente);

                PdfPCell cuenta = new PdfPCell(new Phrase(customer.get("Numero Cuenta")));
                cuenta.setPaddingLeft(4);
                cuenta.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cuenta.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cuenta);

                PdfPCell tipo = new PdfPCell(new Phrase(customer.get("Tipo")));
                tipo.setPaddingLeft(4);
                tipo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tipo.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(tipo);

                PdfPCell saldo = new PdfPCell(new Phrase(customer.get("Saldo Inicial")));
                saldo.setPaddingLeft(4);
                saldo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                saldo.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(saldo);

                PdfPCell estado = new PdfPCell(new Phrase(customer.get("Estado")));
                estado.setPaddingLeft(4);
                estado.setVerticalAlignment(Element.ALIGN_MIDDLE);
                estado.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(estado);

                PdfPCell movimiento = new PdfPCell(new Phrase(customer.get("Movimiento")));
                movimiento.setPaddingLeft(4);
                movimiento.setVerticalAlignment(Element.ALIGN_MIDDLE);
                movimiento.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(movimiento);

                PdfPCell disponible = new PdfPCell(new Phrase(customer.get("Saldo Disponible")));
                disponible.setPaddingLeft(4);
                disponible.setVerticalAlignment(Element.ALIGN_MIDDLE);
                disponible.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(disponible);
            }

            document.add(table);

            document.close();
        } catch (DocumentException e) {
            // logger.error(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
