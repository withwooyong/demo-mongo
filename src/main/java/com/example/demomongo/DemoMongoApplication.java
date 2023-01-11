package com.example.demomongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMongoRepositories
public class DemoMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMongoApplication.class, args);
    }

}
