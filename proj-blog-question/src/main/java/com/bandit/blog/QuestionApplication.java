package com.bandit.blog;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc
@SpringBootApplication
public class QuestionApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }
}