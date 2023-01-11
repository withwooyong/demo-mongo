package com.example.demomongo;

import com.example.demomongo.domain.Comment;
import com.example.demomongo.domain.NoticeBoard;
import com.example.demomongo.domain.User;
import com.example.demomongo.repository.NoticeBoardRepository;
import com.example.demomongo.repository.UserRepository;
import com.example.demomongo.service.NoticeBoardService;
import com.example.demomongo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ContextConfiguration(classes = DemoMongoApplication.class)
//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext
@ActiveProfiles("embed")
class DemoMongoApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NoticeBoardRepository noticeBoardRepository;

    @Autowired
    UserService userService;
    @Autowired
    NoticeBoardService noticeBoardService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void before() {
        this.userRepository.deleteAll();
        this.noticeBoardRepository.deleteAll();
        this.userService = new UserService(mongoTemplate, userRepository);
        this.noticeBoardService = new NoticeBoardService(mongoTemplate, userRepository, noticeBoardRepository);
    }

    // //////////////////////
    // 		도우미 메쏘드들
    // /////////////////////
    User createUser(String name) {
        User user = User.builder().name(name).email(format("%s@test.com", name)).build();
        return userService.save(user);
    }

    void assertUser(User user, String name) {
        assertNotNull(user.getUserId());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertEquals(name, user.getName());
        assertEquals(format("%s@test.com", name), user.getEmail());
    }

    NoticeBoard createNoticeBoard(User owner, String title, String content) {
        NoticeBoard notice = NoticeBoard.builder()
                .ownerId(owner.getUserId())
                .title(title)
                .content(content)
                .build();
        return noticeBoardService.save(notice);
    }

    void assertNoticeBoard(NoticeBoard notice, User owner, String title, String content) {
        assertNotNull(notice.getNoticeId());
        assertNotNull(notice.getCreatedAt());
        assertNotNull(notice.getUpdatedAt());
        assertUser(notice.getOwner(), owner.getName());
        assertEquals(title, notice.getTitle());
        assertEquals(content, notice.getContent());
    }

    Comment createComment(NoticeBoard notice, String userId, String userName, String commentStr) {
        return Comment.builder().userId(userId).userName(userName)
                .comment(commentStr).build();
    }

    void assertComment(Comment comment, String userId, String userName, String commentStr) {
        assertNotNull(comment.getCommentId());
        assertNotNull(comment.getCreatedAt());
        assertEquals(userId, comment.getUserId());
        assertEquals(userName, comment.getUserName());
        assertEquals(commentStr, comment.getComment());
    }


    @DisplayName("유저를 생성한다.")
    @Test
    void test_1() {
        createUser("Ted");
        List<User> allUser = userRepository.findAll();
        assertEquals(1, allUser.size());
        assertUser(allUser.get(0), "Ted");
    }

    @DisplayName("게시글을 작성한다.")
    @Test
    void test_2() {
        User owner = createUser("owner");
        NoticeBoard notice = createNoticeBoard(owner, "공지사항1", "공지1");
        List<NoticeBoard> noticeList = noticeBoardRepository.findAll();
        assertEquals(1, noticeList.size());
        assertDoesNotThrow(() -> {
            NoticeBoard savedNotice = noticeBoardService.findNoticeBoard(notice.getNoticeId()).get();
            assertNoticeBoard(savedNotice, owner, "공지사항1", "공지1");
        });
    }

    @DisplayName("코멘트를 작성한다.")
    @Test
    void test_3() {
        User owner = createUser("owner");
        User user1 = createUser("user1");
        NoticeBoard notice = createNoticeBoard(owner, "공지사항1", "공지1");
        Comment comment = createComment(notice, user1.getUserId(), user1.getName(), "comment1");
        noticeBoardService.addComment(notice.getNoticeId(), comment);

        assertDoesNotThrow(() -> {
            NoticeBoard savedNotice = noticeBoardService.findNoticeBoard(notice.getNoticeId()).get();
            assertEquals(1, savedNotice.getCommentList().size());
            assertComment(savedNotice.getCommentList().get(0), user1.getUserId(), user1.getName(), "comment1");
        });
    }

    @DisplayName("코멘트를 삭제한다.")
    @Test
    void test_4() {
        User owner = createUser("owner");
        User user1 = createUser("user1");
        NoticeBoard notice = createNoticeBoard(owner, "공지사항1", "공지1");
        Comment comment = createComment(notice, user1.getUserId(), user1.getName(), "comment1");
        Comment savedComment = noticeBoardService.addComment(notice.getNoticeId(), comment);
        noticeBoardService.removeComment(notice.getNoticeId(), savedComment.getCommentId());

        assertDoesNotThrow(() -> {
            NoticeBoard savedNotice = noticeBoardService.findNoticeBoard(notice.getNoticeId()).get();
            assertEquals(0, savedNotice.getCommentList().size());
        });
    }

    @DisplayName("코멘트를 삭제한다. – 복잡한 경우")
    @Test
    void test_5() {
        User owner = createUser("owner");
        User user1 = createUser("user1");
        NoticeBoard notice = createNoticeBoard(owner, "공지사항1", "공지1");
        Comment comment1 = noticeBoardService.addComment(notice.getNoticeId(),
                createComment(notice, user1.getUserId(), user1.getName(), "comment1"));
        Comment comment2 = noticeBoardService.addComment(notice.getNoticeId(),
                createComment(notice, user1.getUserId(), user1.getName(), "comment2"));
        Comment comment3 = noticeBoardService.addComment(notice.getNoticeId(),
                createComment(notice, user1.getUserId(), user1.getName(), "comment3"));

        noticeBoardService.removeComment(notice.getNoticeId(), comment2.getCommentId());

        assertDoesNotThrow(() -> {
            NoticeBoard savedNotice = noticeBoardService.findNoticeBoard(notice.getNoticeId()).get();
            assertEquals(2, savedNotice.getCommentList().size());
            assertComment(savedNotice.getCommentList().get(0), user1.getUserId(), user1.getName(), "comment1");
            assertComment(savedNotice.getCommentList().get(1), user1.getUserId(), user1.getName(), "comment3");
        });
    }
}
