package com.example.ex4;

import com.example.ex4.repos.msgRepos;
import com.example.ex4.repos.userRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ex4Application {

    @Autowired
    public userRepos userRepos;

    public static void main(String[] args) {
        SpringApplication.run(Ex4Application.class, args);
    }

}
