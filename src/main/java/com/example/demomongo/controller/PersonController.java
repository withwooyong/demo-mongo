package com.example.demomongo.controller;


import com.example.demomongo.domain.Board;
import com.example.demomongo.domain.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PersonController {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping("/board/id/{id}")
    public Board findById(@PathVariable String id) {
        log.info("findById id={}", id);
        return new Board();
    }

    @PutMapping("/person")
    public void insertPerson() {
        Mono<Person> person = reactiveMongoTemplate.insert(new Person("Bob", 33))
                .then(reactiveMongoTemplate.query(Person.class)
                        .matching(query(where("age").is(33)))
                        .one());
        log.info("person {}", person);
    }
}
