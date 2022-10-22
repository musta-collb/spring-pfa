package com.example.demo.api;

import com.example.demo.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
@RestController
@RequestMapping("/api/file")

public class FileController {
    @Autowired
    FilesStorageService storageService;
    @PostMapping(value = "/upload",consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Try to upload file");
        String message = "";
        try {
            String name = storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(name);
        } catch (IOException e) {
            e.printStackTrace();
            message = "Could not upload the file hellow: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
