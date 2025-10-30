package com.example;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping
    public void save(@RequestBody Storage file){
        storageService.addFile(file);
    }

}
