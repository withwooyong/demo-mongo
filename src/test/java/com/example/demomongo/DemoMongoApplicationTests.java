package com.example.demomongo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
//@ContextConfiguration(classes = DemoMongoApplication.class)
//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//@DirtiesContext
@ActiveProfiles("dev")
class DemoMongoApplicationTests {

    @Test
    void contextLoads() {
    }
}
