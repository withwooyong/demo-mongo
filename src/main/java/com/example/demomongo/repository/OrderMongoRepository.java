package com.example.demomongo.repository;

import com.example.demomongo.domain.OrderCurrent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoRepository extends MongoRepository<OrderCurrent, String> {
}