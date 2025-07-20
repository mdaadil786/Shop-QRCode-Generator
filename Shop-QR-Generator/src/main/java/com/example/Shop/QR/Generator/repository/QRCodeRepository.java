package com.example.Shop.QR.Generator.repository;
import com.example.Shop.QR.Generator.model.QRCodeData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeRepository extends MongoRepository<QRCodeData, String> {
}