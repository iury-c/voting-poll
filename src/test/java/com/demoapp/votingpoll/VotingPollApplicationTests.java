package com.demoapp.votingpoll;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VotingPollApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void applicationContextTest() {
        VotingPollApplication.main(new String[]{});
    }

}
