package com.example.Shop.QR.Generator.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.UUID;

@Service
public class QRCodeService {
    public byte[] generateUPIQRCode(String upiId, String name, String amount, String note, String txnId) throws Exception {
        String upiData = String.format(
                "upi://pay?pa=%s&pn=%s&am=%s&cu=INR&tn=%s - %s",
                upiId, name, amount, note, txnId
        );
        BitMatrix matrix = new MultiFormatWriter().encode(upiData, BarcodeFormat.QR_CODE, 300, 300);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);
        BufferedImage logo = ImageIO.read(new File("src/main/resources/static/logo.png"));
        int deltaHeight = qrImage.getHeight() - logo.getHeight();
        int deltaWidth = qrImage.getWidth() - logo.getWidth();
        BufferedImage combined = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();
        g.drawImage(qrImage, 0, 0, null);
        g.drawImage(logo, deltaWidth / 2, deltaHeight / 2, null);
        g.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(combined, "PNG", baos);
        return baos.toByteArray();
    }
    public String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}