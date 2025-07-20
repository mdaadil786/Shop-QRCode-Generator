package com.example.Shop.QR.Generator.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "upi_qr_codes")
public class QRCodeData {
    @Id
    private String id;
    private String upiId;
    private String name;
    private String amount;
    private String note;
    private String transactionId;
    private LocalDateTime createdAt = LocalDateTime.now();
}
