//package com.example.demomongo.config;
//
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//@Log4j2
//@Configuration
//@EnableMongoRepositories(basePackages = "com.example.demomongo.repository")
////@EnableReactiveMongoRepositories(basePackages = "com.example.**.dto")
//public class MongoDBConfig {
//
//    @Value("${spring.data.mongodb.test.connectionString}")
//    private String connectionString;
//
//    @Bean
//    public MongoDatabaseFactory mongoDatabaseFactory() {
//        log.info("connectionString {}", connectionString);
//        return new SimpleMongoClientDatabaseFactory(connectionString);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoDatabaseFactory());
//    }
//}
