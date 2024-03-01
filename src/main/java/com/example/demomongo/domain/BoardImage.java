package com.example.demomongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "image")
public class BoardImage {


    private String name;
    private String url;

    public BoardImage(String name) {
        this.name = name;
    }
}
