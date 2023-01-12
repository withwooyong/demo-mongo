package com.example.demomongo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collation = "posts")
public class Posts {

    @Transient
    public static final String SEQUENCE_NAME = "posts_sequence";

    @Id
    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
