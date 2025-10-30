package com.example;


import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class Storage {


    private Integer id;
    private String name;
    private String location;
    private String description;

    public Storage(){

    }

    public Storage(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = "";
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id) && Objects.equals(name, storage.name) && Objects.equals(location, storage.location) && Objects.equals(description, storage.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, description);
    }
}
