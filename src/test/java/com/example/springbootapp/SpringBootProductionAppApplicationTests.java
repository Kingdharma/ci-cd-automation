package com.example.springbootapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class SpringBootProductionAppApplicationTests {

    @Test
    void contextLoads() {
    }
}

