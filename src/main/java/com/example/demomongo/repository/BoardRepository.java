package com.example.demomongo.repository;


import com.example.demomongo.domain.Board;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardRepository extends MongoRepository<Board, String> {
}
