package com.learning.file_storage.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.file_storage.playLoad.FileResponse;
import com.learning.file_storage.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image) {
        String fileName;
        try {
            fileName = this.fileService.uploadImage(path, image);
            return new ResponseEntity<>(new FileResponse(fileName, "Image uploaded successfully!"), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace(); // Consider using a logger instead
            return new ResponseEntity<>(new FileResponse(null, "Image upload failed!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
