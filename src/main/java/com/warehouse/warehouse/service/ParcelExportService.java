package com.warehouse.warehouse.service;


import com.google.zxing.WriterException;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.warehouse.warehouse.model.Parcel;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class ParcelExportService {


    public ParcelExportService(CodeService codeService) {
    }


    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(7);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Kod paczki", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Imię nadawcy", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nazwisko nadawcy", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Numer telefonu nadawcy", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Numer telefonu odbiorcy", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Destynacja", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kod QR", font));
        table.addCell(cell);







    }

    private void writeTableData(PdfPTable table, Parcel parcel) throws Exception {

        table.addCell(String.valueOf(parcel.getId()));
        table.addCell(parcel.getFirstName());
        table.addCell(parcel.getLastName());
        table.addCell(String.valueOf(parcel.getSender_telephone()));
        table.addCell(String.valueOf(parcel.getDestination_telephone()));
        table.addCell(String.valueOf(parcel.getDestination_address()));
        table.addCell(String.valueOf(parcel.getEmail()));

    }

    public void exportToPdf(HttpServletResponse response, Parcel parcel) throws Exception {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Paczka", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 2.0f, 2.0f, 2.0f, 2.0f, 2.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, parcel);

        document.add(table);

        document.close();

    }

    public void exportToCSV(HttpServletResponse response, Parcel parcel) throws DocumentException, IOException {

        CsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_PREFERENCE);

        String[] csvHeader = {"Kod paczki", "Imie nadawcy", "Nazwisko nadawcy", "Numer tel nadawcy", "Numer tel odbiorcy", "Destynacja", "Email"};
        String[] nameMapping = {
                "id",
                "firstName",
                "lastName",
                "sender_telephone",
                "destination_telephone",
                "destination_address",
                "email"
        };

        csvWriter.writeHeader(csvHeader);
        csvWriter.write(parcel, nameMapping);

        csvWriter.close();

    }

}
