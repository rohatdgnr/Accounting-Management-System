package art.deerborg.accounting.v1.api.core.utilities;

import art.deerborg.accounting.v1.api.dto.response.invoice.InvoiceDetailResponse;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;


import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

public class PdfGenerator {

    public static byte[] createPdf(InvoiceDetailResponse response) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // Colors
        Color headerColor = new DeviceRgb(63, 169, 219);
        Color tableHeaderColor = new DeviceRgb(0, 121, 182);
        Color tableCellColor = new DeviceRgb(240, 240, 240);
        Color whiteColor = new DeviceRgb(255, 255, 255);

        // Add title and logo
        document.add(new Paragraph("BANK NAME")
                .setFontSize(20)
                .setFontColor(headerColor)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold());

        document.add(new Paragraph("Bank Statement")
                .setFontSize(18)
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setMarginBottom(20));

        // Add customer and transaction details
        document.add(new Paragraph("Customer: " + response.getCustomer().getName())
                .setFontSize(12)
                .setBold());
        document.add(new Paragraph("Phone Number: " + response.getCustomer().getPhone())
                .setFontSize(12));
        document.add(new Paragraph("Statement Date: " + LocalDate.now())
                .setFontSize(12));
        document.add(new Paragraph(" ")
                .setFontSize(12));

        // Add invoice details
        document.add(new Paragraph("Created Date: " + LocalDate.now())
                .setFontSize(12));
        document.add(new Paragraph("ID: " + response.getId())
                .setFontSize(12));
        document.add(new Paragraph("Sales Date: " + response.getDate())
                .setFontSize(12));
        document.add(new Paragraph("Quantity: " + response.getQuantity())
                .setFontSize(12));
        document.add(new Paragraph("Total Amount: " + response.getTotalAmount())
                .setFontSize(12));
        document.add(new Paragraph("Net Amount: " + response.getNetAmount())
                .setFontSize(12));
        document.add(new Paragraph("Tax Amount: " + response.getTaxAmount())
                .setFontSize(12));

        // Add product details
        document.add(new Paragraph("Customer: " + response.getCustomer().getName())
                .setFontSize(12));
        document.add(new Paragraph("Product: " + response.getProduct().getBrand())
                .setFontSize(12));

        // Add transaction table
        float[] columnWidths = {1, 5, 2, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Table headers
        table.addHeaderCell(new Paragraph("ID")
                .setBackgroundColor(tableHeaderColor)
                .setFontColor(whiteColor)
                .setBold());
        table.addHeaderCell(new Paragraph("Description")
                .setBackgroundColor(tableHeaderColor)
                .setFontColor(whiteColor)
                .setBold());
        table.addHeaderCell(new Paragraph("Amount")
                .setBackgroundColor(tableHeaderColor)
                .setFontColor(whiteColor)
                .setBold());
        table.addHeaderCell(new Paragraph("Date")
                .setBackgroundColor(tableHeaderColor)
                .setFontColor(whiteColor)
                .setBold());

        // Adding sample data to the table
        table.addCell(new Paragraph(String.valueOf(response.getId())).setBackgroundColor(tableCellColor));
        table.addCell(new Paragraph("Transaction Description").setBackgroundColor(tableCellColor));
        table.addCell(new Paragraph(String.valueOf(response.getTotalAmount())).setBackgroundColor(tableCellColor));
        table.addCell(new Paragraph(response.getDate().toString()).setBackgroundColor(tableCellColor));

        // Add more rows as needed
        // For example, loop through a list of transactions and add rows

        document.add(table);

        // Add summary
        document.add(new Paragraph(" ")
                .setFontSize(12));
        document.add(new Paragraph("Summary")
                .setFontSize(14)
                .setBold());
        document.add(new Paragraph("Total Amount: " + response.getTotalAmount())
                .setFontSize(12));
        document.add(new Paragraph("Net Amount: " + response.getNetAmount())
                .setFontSize(12));
        document.add(new Paragraph("Tax Amount: " + response.getTaxAmount())
                .setFontSize(12));

        // Close document
        document.close();
        return baos.toByteArray();
    }
}
