package com.example.mongodbblob.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.example.mongodbblob.models.ImageMetadata;
import com.example.mongodbblob.repositories.ImageMetadataRepository;

import jakarta.annotation.PostConstruct;
/* 
@Service
public class ImageMetadataService {
    

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;

    @Value("{spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("{azure.blob-storage.connection-string}")
    private String connectionString;

    private BlobServiceClient blobServiceClient;

    public List<ImageMetadata> findAll() {
        return imageMetadataRepository.findAll();
    }

    @PostConstruct
    public void init() {
        blobServiceClient = new BlobServiceClientBuilder()
            .connectionString(connectionString)
            .buildClient();
    }

    public String uploadImageWithCaption (MultipartFile image, String caption) throws IOException{
        String blobFilename = image.getOriginalFilename();
        BlobClient blobClient = blobServiceClient
            .getBlobContainerClient(containerName)
            .getBlobClient(blobFilename);

        blobClient.upload(image.getInputStream(), image.getSize(), true);

        String imageUrl = blobClient.getBlobUrl();

        ImageMetadata metadata = new ImageMetadata();
        metadata.setCaption(caption);
        metadata.setImageUrl(imageUrl);
        
        imageMetadataRepository.save(metadata);

        return "Image uploaded";
    }


}*/

@Service
public class ImageMetadataService {

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;
    
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;
    
    @Value("${azure.blob-storage.connection-string}")
    private String connectionString;

    private BlobServiceClient blobServiceClient;

    @PostConstruct
    public void init() {
        blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
    }

    public ImageMetadata save(ImageMetadata metadata) {
        return imageMetadataRepository.save(metadata);
    }

    public List<ImageMetadata> findAll() {
        return imageMetadataRepository.findAll();
    }

    public Optional<ImageMetadata> findById(String id) {
        return imageMetadataRepository.findById(id);
    }
    
    public String uploadImageWithCaption(MultipartFile imageFile, String caption) throws IOException {
        String blobFileName = imageFile.getOriginalFilename();
        BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobFileName);

        blobClient.upload(imageFile.getInputStream(), imageFile.getSize(), true);

        String imageUrl = blobClient.getBlobUrl();
        
        ImageMetadata metadata = new ImageMetadata();
        metadata.setCaption(caption);
        metadata.setImageUrl(imageUrl);
        
        imageMetadataRepository.save(metadata);

        return "Image and metadata uploaded successfully!";
    }
}
