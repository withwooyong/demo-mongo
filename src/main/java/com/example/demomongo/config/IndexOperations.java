package com.example.demomongo.config;

import org.springframework.data.mongodb.core.index.IndexDefinition;
import org.springframework.data.mongodb.core.index.IndexInfo;
import org.springframework.data.mongodb.core.index.IndexOptions;

import java.util.List;

public interface IndexOperations {
    String ensureIndex(IndexDefinition indexDefinition);

    void alterIndex(String name, IndexOptions options);

    void dropIndex(String name);

    void dropAllIndexes();

    List<IndexInfo> getIndexInfo();
}
