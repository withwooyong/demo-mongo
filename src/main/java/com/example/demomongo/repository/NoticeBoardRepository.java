package com.example.demomongo.repository;

import com.example.demomongo.domain.NoticeBoard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeBoardRepository extends MongoRepository<NoticeBoard, String> {
}
