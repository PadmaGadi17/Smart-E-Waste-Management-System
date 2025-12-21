package com.sample.Smart.E_waste.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileStorageService {
    private final Path root = Path.of("uploads");

    public String save(MultipartFile file) {
        try{
            if(!Files.exists(root)) Files.createDirectories(root);
            String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path p = root.resolve(name);
            file.transferTo(p.toFile());
            return "/uploads/" + name;
        }catch(Exception e){
            return null;
        }
    }
}



