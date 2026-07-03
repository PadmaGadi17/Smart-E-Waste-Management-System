package com.sample.Smart.E_waste.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    private final Path root = Paths.get(System.getProperty("user.home"), "uploads");

    public String save(MultipartFile file) {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            String original = file.getOriginalFilename();
            if (original == null || original.isBlank()) {
                throw new RuntimeException("Invalid filename");
            }

            String safeName = System.currentTimeMillis() + "_" +
                    original.replace(" ", "_");

            Path dest = root.resolve(safeName);
            Files.copy(file.getInputStream(), dest);

            return "/uploads/" + safeName;

        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }
}

