package com.warehouse.service;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lowagie.text.Image;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class CodeService {


   /* public static void generateQRCodeImage(String text)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 400, 400);
        String filePath = "./src/main/resources/" + text + UUID.randomUUID() + ".png";
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
*/

    public static Image getQRCodeImage(String text) throws WriterException, IOException {
        final QRCodeWriter qrCodeWriter = new QRCodeWriter();
        final BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 400, 400);
        final ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return Image.getInstance(String.valueOf(bitMatrix));
    }

    public static Image generateQRCodeImage(UUID barcodeText) throws Exception {
        final BufferedImage image = new BufferedImage(250,
                250,
                BufferedImage.TYPE_BYTE_GRAY);
        final ByteArrayOutputStream stream = QRCode
                .from(String.valueOf(barcodeText))
                .withSize(250, 250)
                .stream();
        ImageIO.write(image, "png", stream);

        return Image.getInstance(stream.toByteArray());
    }

    @Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }
}
