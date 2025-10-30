package com.example;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

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

    public void addFile(Storage file) {

        Optional<Storage> existing = storageRepository.findByNameAndLocationAndDescription(file.getName(), file.getLocation(), file.getDescription());
        if (existing.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate Entry");
        }

        storageRepository.save(file);
        System.out.println("storage size : "+ storageRepository.count());

    }
}
