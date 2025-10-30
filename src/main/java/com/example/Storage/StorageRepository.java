package com.example.Storage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageRepository
    extends JpaRepository<Storage,Integer> {
    Optional<Storage> findByNameAndLocationAndDescription(String name, String location, String description);
}
