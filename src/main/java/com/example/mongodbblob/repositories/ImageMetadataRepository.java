package com.example.mongodbblob.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mongodbblob.models.ImageMetadata;

public interface ImageMetadataRepository extends MongoRepository<ImageMetadata, String> {
    
}
