package com.example.springbootapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootProductionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProductionAppApplication.class, args);
    }
}

