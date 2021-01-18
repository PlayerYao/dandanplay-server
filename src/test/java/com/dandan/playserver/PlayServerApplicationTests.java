package com.dandan.playserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class PlayServerApplicationTests {

    @Autowired
    private PlayServerApplication playServerApplication;
    @Test
    void contextLoads() {
        String ping = playServerApplication.ping();
        assertNull(ping);
    }

}
