package com.example.demomongo.controller;

import com.example.demomongo.domain.Image;
import com.example.demomongo.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/image/find-all")
    public Flux<Image> findAll() {
        return imageService.findAll();
    }

    @GetMapping("/image/find-name/{name}")
    public Mono<Image> findByName(@PathVariable String name) {
        return imageService.findByName(name);
    }
}
