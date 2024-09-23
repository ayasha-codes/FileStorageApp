package com.learning.file_storage.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.file_storage.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // Validate file type (optional)
        
        // File name
        String name = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));
        // Full path
        String fullPath = Paths.get(path, fileName).toString();

        // Create folder if it does not exist
        File directory = new File(path);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("Failed to create directory: " + path);
            }
        }

        // File copy
        Files.copy(file.getInputStream(), Paths.get(fullPath));
        
        return fileName;
    }
}
