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
@Document(collection = "current")
public class OrderCurrent {

    @Id
    private String id;

    @Indexed
    private String name;
    private String job;

//    @CreatedDate
//    private LocalDateTime createdAt;

    public OrderCurrent(String name, String job) {
        this.name = name;
        this.job = job;
    }
}