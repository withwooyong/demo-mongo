package com.example.demomongo.init;

import com.example.demomongo.domain.Board;
import com.example.demomongo.domain.Image;
import com.example.demomongo.domain.NonIdBoard;
import com.example.demomongo.domain.OrderCurrent;
import com.example.demomongo.domain.Person;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class InitDatabase {
    @Bean
    CommandLineRunner init(MongoTemplate mongoTemplate) {
        return args -> {
            mongoTemplate.dropCollection(Person.class);
            mongoTemplate.insert(new Person("name1", 10));
            mongoTemplate.insert(new Person("name2", 20));
            mongoTemplate.insert(new Person("name3", 30));
            mongoTemplate.insert(new Person("name4", 40));


            mongoTemplate.dropCollection(Image.class);
            mongoTemplate.insert(new Image("test1.jpg"));
            mongoTemplate.insert(new Image("test2.jpg"));
            mongoTemplate.insert(new Image("test3.jpg"));
            mongoTemplate.insert(new Image("test4.jpg"));
            mongoTemplate.findAll(Image.class).forEach(image -> {
                System.out.println(image.toString());
            });

            mongoTemplate.dropCollection(Board.class);
            mongoTemplate.insert(new Board("title1", "content1"));
            mongoTemplate.insert(new Board("title2", "content2"));
            mongoTemplate.insert(new Board("title3", "content3"));
            mongoTemplate.insert(new Board("title4", "content4"));
            mongoTemplate.findAll(Board.class).forEach(board -> {
                System.out.println(board.toString());
            });

            mongoTemplate.dropCollection(OrderCurrent.class);
            mongoTemplate.insert(new OrderCurrent("name1", "job1"));
            mongoTemplate.insert(new OrderCurrent("name2", "job2"));
            mongoTemplate.insert(new OrderCurrent("name3", "job3"));
            mongoTemplate.insert(new OrderCurrent("name4", "job4"));
            mongoTemplate.findAll(OrderCurrent.class).forEach(orderCurrent -> {
                System.out.println(orderCurrent.toString());
            });

            mongoTemplate.dropCollection(NonIdBoard.class);
            mongoTemplate.insert(new NonIdBoard("title1", "content1"));
            mongoTemplate.insert(new NonIdBoard("title2", "content2"));
            mongoTemplate.insert(new NonIdBoard("title3", "content3"));
            mongoTemplate.insert(new NonIdBoard("title4", "content4"));
            mongoTemplate.findAll(NonIdBoard.class).forEach(nonIdBoard -> {
                System.out.println(nonIdBoard.toString());
            });
        };
    }
}
