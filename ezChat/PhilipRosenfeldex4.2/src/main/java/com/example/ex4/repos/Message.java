package com.example.ex4.repos;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    //@NotBlank(message = "you cant send an empty message")
    private String message1;
    @CreationTimestamp
    private Date date;

    public Message() {}

    public Message(String msg) {
        this.message1 = msg;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMessage1(String msg) { this.message1 = msg; }

    public String getMessage1() { return message1; }

    public void setDate(Date date) { this.date = date; }

    public Date getDate() { return date; }

    @Override
    public String toString() {
        return "Msg{ name=" + name +", message1=" + message1 +", id=" + id +'}';
    }
}

