package com.example.demomongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "board")
public class Board {

    @Id
    private String id;

    @Indexed
    private String title;
    private String content;

//    @CreatedDate
//    LocalDateTime createdAt;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
