package com.TourConnect.TourConnect.presentation.controllers;


import com.TourConnect.TourConnect.application.services.IPaymentService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final Path uploadDir = Paths.get("uploads");
    IPaymentService paymentService;

    public FileController(IPaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // ✅ Dosya yükleme (örneğin dekont)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("paymentId") UUID paymentId // 💡 Payment ID'yi parametre olarak al
    ) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Dosya boş olamaz.");
            }

            String filename = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
            Path targetPath = uploadDir.resolve(filename);

            Files.createDirectories(uploadDir);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            // ✅ Dosya başarıyla yüklendikten sonra, veritabanında receipt yolunu güncelle
            String filePath = "/api/files/uploads/" + filename;
            paymentService.updateReceiptPath(paymentId, filePath);

            return ResponseEntity.ok("Dosya yüklendi: " + filePath);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Dosya yüklenemedi.");
        }
    }


    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            // Dosya yolunu oluştur
            Path filePath = uploadDir.resolve(filename).normalize();
            File file = filePath.toFile(); // Dosyayı File nesnesine çevir

            // Dosyanın varlığını kontrol et
            if (file.exists() && file.canRead()) {
                Resource resource = new FileSystemResource(file); // Dosyayı bir resource olarak al

                // Dosyayı başarılı bir şekilde döndür
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_OCTET_STREAM) // Dosya tipi belirle (Genel tip)
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build(); // Dosya bulunamadıysa 404 dön
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // Hata durumunda 400 dön
        }
    }



}
