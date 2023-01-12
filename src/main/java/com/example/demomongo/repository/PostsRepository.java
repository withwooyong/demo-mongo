package com.example.demomongo.repository;

import com.example.demomongo.domain.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Posts, String> {
}
