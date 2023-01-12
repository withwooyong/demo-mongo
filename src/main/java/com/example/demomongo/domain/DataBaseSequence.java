package com.example.demomongo.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Getter
@Service
@Document(collation = "database_sequences")
public class DataBaseSequence {

    @Id
    private String id;
    private Long seq;
}
