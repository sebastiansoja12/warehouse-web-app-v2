package com.warehouse.warehouse.service;


import com.google.zxing.WriterException;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfDocument;
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
        cell.setBackgroundColor(Color.gray);
        cell.setPadding(2);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Kod", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Dane nadawcy", font));
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
        table.addCell(parcel.getFirstName() + " " + parcel.getLastName());
        table.addCell(String.valueOf(parcel.getSenderTelephone()));
        table.addCell(String.valueOf(parcel.getRecipientTelephone()));
        table.addCell(String.valueOf(parcel.getRecipientCity()));
        table.addCell(String.valueOf(parcel.getRecipientEmail()));
        table.addCell(CodeService.generateQRCodeImage(parcel.getId()));

    }
    private void getQRCode(PdfPTable table, Parcel parcel) throws Exception {
    }

    public void exportToPdf(HttpServletResponse response, Parcel parcel) throws Exception {
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
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
