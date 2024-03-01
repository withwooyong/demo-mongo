package com.example.demomongo.repository;


import com.example.demomongo.domain.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends MongoRepository<Board, String> {
//public interface BoardRepository extends ReactiveCrudRepository<Board, String> {

    Board findByTitle(String title);

    Board findByContent(String content);
//    Flux<Board> findByTitle(String title);
}
