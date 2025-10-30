package com.example;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {

    private final List<Storage> storage;

    public StorageService() {
        this.storage = new ArrayList<>();
    }


    public Storage getStorage(Integer id) {
        return storage.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Storage not found"
                ));
    }


    public List<Storage> getStorages() {
        return storage;
    }

    public void setStorage(Storage file) {
        storage.add(file);
        System.out.println("storage size : "+ storage.size());

    }
}
