package com.example.demomongo.service;

import com.example.demomongo.domain.Image;
import com.example.demomongo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Log4j2
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private static final String UPLOAD_ROOT = "upload-dir";

    public Flux<Image> findAll() {
        log.info("findAll");
        return imageRepository.findAll().doOnNext(image -> log.info(image.toString()));
    }

    public Mono<Image> findByName(String name) {
        return imageRepository.findByName(name)
                .doOnNext(image -> log.debug("Found image: {}", image.toString()));
    }


    public Mono<Void> uploadImage(Flux<FilePart> files) {
        return files.flatMap(file -> {
            Mono<Image> saveDatabaseImage = imageRepository.save(
                    new Image(file.filename()));
            Path destPath = Paths.get(UPLOAD_ROOT, file.filename());
            // 파일을 비동기적으로 복사하는 로직
            Mono<Void> copyFile = DataBufferUtils.write(file.content(), destPath, StandardOpenOption.CREATE)
                    .then()
                    .log("createImage-copy");
            return Mono.when(saveDatabaseImage, copyFile);
        }).then();
    }

    /**
     * 파일 삭제 작업이 Schedulers.boundedElastic() 스케줄러에서 제공하는 별도의 스레드 풀에서 비동기적으로 실행.
     * 이는 리액터 컨텍스트의 메인 스레드를 블로킹하지 않으므로,
     * 리액티브 프로그래밍 모델의 비동기적 특성을 유지하면서 블로킹 I/O 작업을 처리
     */
    public Mono<Void> deleteImage2(String filename) {
        // 데이터베이스에서 이미지 정보를 삭제하는 Mono
        Mono<Void> deleteDatabaseImage = imageRepository.findByName(filename)
                .flatMap(imageRepository::delete)
                .then();

        // 파일 시스템에서 파일을 삭제하는 Mono
        Mono<Void> deleteFile = Mono.fromCallable(() -> {
                    Path path = Paths.get(UPLOAD_ROOT, filename);
                    return Files.deleteIfExists(path);
                })
                .subscribeOn(Schedulers.boundedElastic()) // 블로킹 I/O 작업을 위한 스케줄러 사용
                .then();

        // 두 작업이 모두 완료될 때까지 기다린 후 완료 신호를 발생시킵니다.
        return Mono.when(deleteDatabaseImage, deleteFile).then();
    }
}
