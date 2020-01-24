package com.example.ex4.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface userRepos extends JpaRepository<User, Long> {
    //function to get aii activity users
    List<User> findByName(String name);
}

