package com.example.Storage;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/s3/v1/upload")
public class StorageController {

    /*
    Can use auto wired here for the variable?
     */
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/") // explicitly maps GET /api/s3/v1/upload/
    public List<Storage> getFiles(){
        return storageService.getStorages();
    }

    @GetMapping("{id}")
    public Storage getFile(
            @PathVariable Integer id
    ){
        return storageService.getStorage(id);
    }

    @GetMapping("/wellness")
    public String checkStatus(){
        return "Server is alive";
    }

    @PostMapping("/file")
    public void uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("description") String description
    ){
        storageService.storeFile(file, name, description);
    }

}
