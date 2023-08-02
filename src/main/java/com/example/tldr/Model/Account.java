package com.example.tldr.Model;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Serializable;
import java.time.LocalDateTime;

@SpringBootApplication
public class Account implements Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime timeStamp;

    public Account() {}

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        timeStamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDt() {
        return timeStamp;
    }

    public void setDt(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
