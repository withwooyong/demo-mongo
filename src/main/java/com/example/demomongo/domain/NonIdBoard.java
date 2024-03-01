package com.example.demomongo.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@Document(collection = "non-id-board")
public class NonIdBoard {


    @Indexed
    private String title;
    private String content;

    public NonIdBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
