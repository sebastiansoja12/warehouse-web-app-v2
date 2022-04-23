package com.warehouse.warehouse.service;


import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.warehouse.warehouse.model.Parcel;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
public class ParcelExportService {


    private void writeTableHeader(PdfPTable senderTable, PdfPTable recipientTable) {
        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.gray);
        cell.setPadding(2);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Kod", font));
        senderTable.addCell(cell);

        cell.setPhrase(new Phrase("Dane nadawcy", font));
        senderTable.addCell(cell);

        cell.setPhrase(new Phrase("Numer telefonu nadawcy", font));
        senderTable.addCell(cell);


        cell.setPhrase(new Phrase("Dane odbiorcy", font));
        recipientTable.addCell(cell);

        cell.setPhrase(new Phrase("Numer telefonu", font));
        recipientTable.addCell(cell);

        cell.setPhrase(new Phrase("Destynacja", font));
        recipientTable.addCell(cell);

        cell.setPhrase(new Phrase("Email odbiorcy", font));
        recipientTable.addCell(cell);

        cell.setPhrase(new Phrase("Kod QR", font));
        recipientTable.addCell(cell);

    }


    private void writeTableData(PdfPTable senderTable, PdfPTable recipientTable, Parcel parcel) throws Exception {

        //sender
        senderTable.addCell(String.valueOf(parcel.getId()));
        senderTable.addCell(parcel.getFirstName() + " " + parcel.getLastName());
        senderTable.addCell(String.valueOf(parcel.getSenderTelephone()));

        //recipient
        recipientTable.addCell(parcel.getRecipientFirstName() + " " + parcel.getRecipientLastName());
        recipientTable.addCell(String.valueOf(parcel.getRecipientTelephone()));
        recipientTable.addCell((parcel.getRecipientCity()) + " " + parcel.getRecipientStreet());
        recipientTable.addCell(String.valueOf(parcel.getRecipientEmail()));
        recipientTable.addCell(CodeService.generateQRCodeImage(parcel.getId()));


    }

    public void exportToPdf(HttpServletResponse response, Parcel parcel) throws Exception {
        Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        PdfPTable senderTable = new PdfPTable(3);
        PdfPTable recipientTable = new PdfPTable(5);


        senderTable.setWidthPercentage(100f);
        senderTable.setSpacingBefore(10);

        recipientTable.setWidthPercentage(100f);
        recipientTable.setSpacingBefore(10);


        writeTableHeader(senderTable, recipientTable);
        writeTableData(senderTable, recipientTable, parcel);
        document.add(senderTable);
        document.add(recipientTable);

        document.close();

    }

    public void exportToCSV(HttpServletResponse response, Parcel parcel) throws DocumentException, IOException {

        CsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);

        String[] csvHeader = {"Kod paczki", "Imie nadawcy", "Nazwisko nadawcy", "Numer tel nadawcy",
                "Imie odbiorcy", "Nazwisko odbiorcy", "Numer tel odbiorcy", "Miasto", "Ulica", "Email"};
        String[] nameMapping = {
                "id",
                "firstName",
                "lastName",
                "senderTelephone",
                "recipientFirstName",
                "recipientLastName",
                "recipientTelephone",
                "recipientCity",
                "recipientStreet",
                "recipientEmail"
        };

        csvWriter.writeHeader(csvHeader);
        csvWriter.write(parcel, nameMapping);

        csvWriter.close();

    }

}
