package com.example.demomongo.service;

import com.example.demomongo.domain.Comment;
import com.example.demomongo.domain.NoticeBoard;
import com.example.demomongo.repository.NoticeBoardRepository;
import com.example.demomongo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoticeBoardService {
    private final MongoTemplate mongoTemplate;

    private final UserRepository userRepository;

    private final NoticeBoardRepository noticeBoardRepository;

    public NoticeBoard save(NoticeBoard notice) {
        if (ObjectUtils.isEmpty(notice.getNoticeId())) {
            notice.setCreatedAt(LocalDateTime.now());
        }
        notice.setUpdatedAt(LocalDateTime.now());
        return noticeBoardRepository.save(notice);
    }

    public Optional<NoticeBoard> findNoticeBoard(String noticeId) {
        return noticeBoardRepository.findById(noticeId).map(notice -> {
            userRepository.findById(notice.getOwnerId()).ifPresent(owner -> notice.setOwner(owner));
            return notice;
        });
    }

    public Comment addComment(String noticeId, Comment comment) {
        comment.setCommentId(ObjectId.get().toHexString());
        comment.setCreatedAt(LocalDateTime.now());
        Update update = new Update();
        update.push("commentList", comment);
        mongoTemplate.findAndModify(Query.query(Criteria.where("noticeId").is(noticeId)),
                update, NoticeBoard.class);
        return comment;
    }

    public Optional<NoticeBoard> removeComment(String noticeId, String commentId) {
        Update update = new Update();
        update.pull("commentList", Query.query(Criteria.where("commentId").is(commentId)));
        mongoTemplate.findAndModify(Query.query(Criteria.where("noticeId").is(noticeId)),
                update, NoticeBoard.class);
        return findNoticeBoard(noticeId);
    }
}
