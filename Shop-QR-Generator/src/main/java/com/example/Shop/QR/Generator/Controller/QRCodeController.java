package com.example.Shop.QR.Generator.Controller;
import com.example.Shop.QR.Generator.Service.QRCodeService;
import com.example.Shop.QR.Generator.model.QRCodeData;
import com.example.Shop.QR.Generator.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private QRCodeRepository repository;

    @GetMapping(value = "/generateQR", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(
            @RequestParam String upiId,
            @RequestParam String name,
            @RequestParam(defaultValue = "0") String amount,
            @RequestParam(defaultValue = "Shop Payment") String note
    ) throws Exception {

        String txnId = qrCodeService.generateTransactionId();
        QRCodeData data = new QRCodeData();
        data.setUpiId(upiId);
        data.setName(name);
        data.setAmount(amount);
        data.setNote(note);
        data.setTransactionId(txnId);
        repository.save(data);
        byte[] qr = qrCodeService.generateUPIQRCode(upiId, name, amount, note, txnId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qr);
    }
}