package com.TourConnect.TourConnect.application.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String store(MultipartFile file);

}
