package com.example.demomongo.service;

import com.example.demomongo.domain.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

@SpringBootTest
class ImageServiceTest {

    @Autowired
    ImageService imageService;

    @Test
    void findAll() {
        Flux<Image> all = imageService.findAll();
        all.subscribe(image -> System.out.println("Image: " + image));
    }

    @Test
    void uploadImage() {
    }

    @Test
    void deleteImage2() {
    }
}