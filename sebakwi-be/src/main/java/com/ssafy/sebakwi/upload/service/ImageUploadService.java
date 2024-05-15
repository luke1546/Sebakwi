package com.ssafy.sebakwi.upload.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class ImageUploadService {

    private static final String UPLOAD_DIR = "files";

    public void handleGifUpload(String img_name,  MultipartFile file) {


        try {
            Path uploadPath = Paths.get(UPLOAD_DIR, String.valueOf(img_name));
            Files.createDirectories(uploadPath.getParent());

            Files.write(uploadPath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
