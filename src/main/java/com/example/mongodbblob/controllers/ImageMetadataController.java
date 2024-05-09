package com.example.mongodbblob.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mongodbblob.models.ImageMetadata;
import com.example.mongodbblob.services.ImageMetadataService;
import org.springframework.web.bind.annotation.RequestParam;

/* 
@RestController
@RequestMapping("image-metadata")

public class ImageMetadataController {
    
    @Autowired
    private ImageMetadataService imageMetadataService;

    @GetMapping("/")
    public List<ImageMetadata> getAllImageMetadata() {
        return imageMetadataService.findAll();
    }

    @GetMapping("/upload")
    public String uploadImage (@RequestParam("image") MultipartFile image,
        @RequestParam("caption") String caption) throws IOException{
        
        return imageMetadataService.uploadImageWithCaption(image, caption);
    }

    

    @GetMapping("/deneme")
    public String getMethodName(@RequestParam String param) {
        return new String("Hello World");
    }
    
    

} */

@RestController
@RequestMapping("/image-metadata")
public class ImageMetadataController {
	
	@Autowired
	private ImageMetadataService imageMetadataService;
	
	@PostMapping("/upload")
	public String uploadImageWithCaption(@RequestParam("image") MultipartFile imageFile, @RequestParam("caption") String caption) throws IOException {
		return imageMetadataService.uploadImageWithCaption(imageFile, caption);
	}
	
	@GetMapping("/")
	public List<ImageMetadata> getAllImageMetadata() {
		return imageMetadataService.findAll();
	}
	
	@GetMapping("/{id}")
	public ImageMetadata getImageMetadataById(@PathVariable String id) {
		return imageMetadataService.findById(id).orElse(null);
	}
}
