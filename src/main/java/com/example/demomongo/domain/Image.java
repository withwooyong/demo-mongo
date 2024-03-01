package com.example.demomongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "image")
public class Image {

    @Id
    private String id;

    private String name;

    @CreatedDate
    LocalDateTime createdAt;

    public Image(String name) {
        this.name = name;
    }
}
