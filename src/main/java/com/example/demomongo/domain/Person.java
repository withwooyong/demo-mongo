package com.example.demomongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class Person {

    private String id;
    private String name;
    private int age;

    @CreatedDate
    private LocalDateTime createdAt;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}