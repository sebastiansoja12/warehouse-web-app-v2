package com.warehouse.barcode.domain.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.warehouse.barcode.domain.model.Parcel;
import com.warehouse.barcode.infrastructure.adapter.secondary.mapper.ParcelEntityMapper;
import com.warehouse.shipment.infrastructure.adapter.entity.ParcelEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@Service
@AllArgsConstructor
public class ParcelExportService {

    private final BarcodeGeneratorServiceImpl generatorService;

    private final ParcelEntityMapper entityMapper;

    private void writeTableHeader(PdfPTable senderTable, PdfPTable recipientTable) {
        final PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.gray);
        cell.setPadding(2);

        final com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
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
        senderTable.addCell(parcel.getSender().getFirstName() + " " + parcel.getSender().getLastName());
        senderTable.addCell(String.valueOf(parcel.getSender().getTelephoneNumber()));

        //recipient
        recipientTable.addCell(parcel.getRecipient().getFirstName() + " " + parcel.getRecipient().getLastName());
        recipientTable.addCell(String.valueOf(parcel.getRecipient().getTelephoneNumber()));
        recipientTable.addCell((parcel.getRecipient().getCity()) + " " + parcel.getRecipient().getStreet());
        recipientTable.addCell(String.valueOf(parcel.getRecipient().getEmail()));
        recipientTable.addCell(generatorService.generateQRCodeImage(parcel.getId()));


    }

    public void exportToPdf(HttpServletResponse response, ParcelEntity parcelEntity) throws Exception {
        final Document document = new Document(PageSize.LETTER);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        final Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        final PdfPTable senderTable = new PdfPTable(3);
        final PdfPTable recipientTable = new PdfPTable(5);


        senderTable.setWidthPercentage(100f);
        senderTable.setSpacingBefore(10);

        recipientTable.setWidthPercentage(100f);
        recipientTable.setSpacingBefore(10);


        writeTableHeader(senderTable, recipientTable);

        final Parcel parcel = entityMapper.map(parcelEntity);

        writeTableData(senderTable, recipientTable, parcel);

        document.add(senderTable);
        document.add(recipientTable);
        document.close();
    }
}

