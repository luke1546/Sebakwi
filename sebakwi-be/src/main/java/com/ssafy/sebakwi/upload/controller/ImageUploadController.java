package com.ssafy.sebakwi.upload.controller;

import com.ssafy.sebakwi.upload.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequestMapping("/api/upload")
@RequiredArgsConstructor
@Slf4j
@RestController
public class ImageUploadController {
    final ImageUploadService imageUploadService;

    @PostMapping("/checkup/{img_name}")
    public String uploadGif(@PathVariable String img_name, @RequestPart("file") MultipartFile file) {
        log.info("uuid={}, checkuo gif 파일 업로드중....", img_name);
        
        imageUploadService.handleGifUpload(img_name, file);
        
        log.info("uuid={}, checkuo gif 파일 업로드 성공....", img_name);
        return "upload Success";
    }
}
