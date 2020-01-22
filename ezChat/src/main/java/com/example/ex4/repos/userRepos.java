package com.example.ex4.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepos extends JpaRepository<User, Long> {
    //solange function
    List<User> findByName(String name);

    //List<User> findByUserName(String name);
}

