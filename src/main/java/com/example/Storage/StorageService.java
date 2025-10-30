package com.example.Storage;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private final StorageRepository storageRepository;
    String uploadPath = "./uploads/";

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }


    public Storage getStorage(Integer id) {
        return storageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public List<Storage> getStorages() {
        return storageRepository.findAll();
    }

    public void storeFile(MultipartFile file, String name, String description) {

        String location = uploadPath + name;
        Optional<Storage> existing = storageRepository.findByNameAndLocationAndDescription(name, location, description);
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate Entry");
        }
        try{
            Storage storage = new Storage();
            storage.setName(name);
            storage.setDescription(description);
            storage.setLocation(saveFiletoDisk(file,name));
            storageRepository.save(storage);
        }catch (Exception e) {
            throw new RuntimeException("Error storing file", e);
        }
    }

    private String saveFiletoDisk(MultipartFile file, String name) throws IOException {
        // Get original filename's extension
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        String filename = name + extension;

        // Create absolute path
        Path uploadDir = Paths.get(uploadPath).toAbsolutePath();
        Files.createDirectories(uploadDir);

        Path filePath = uploadDir.resolve(filename);
        file.transferTo(filePath.toFile());

        return filePath.toString();  // Return absolute path
    }
}
