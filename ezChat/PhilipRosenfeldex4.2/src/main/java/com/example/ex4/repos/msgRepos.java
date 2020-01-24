package com.example.ex4.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface msgRepos extends JpaRepository<Message, Long> {
    //get all message's
    List<Message> findAll();

    //get message's by user
    List<Message> findByName(String name);

    //get message's by text
    @Query(value = "SELECT * FROM Message m WHERE m.message1 LIKE CONCAT('%',:message,'%')", nativeQuery = true)
    List<Message> findByMessage1(@Param("message") String message);
}

