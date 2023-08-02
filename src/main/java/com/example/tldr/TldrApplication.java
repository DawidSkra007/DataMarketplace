package com.example.tldr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.sql.Timestamp;

@SpringBootApplication
public class TldrApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(TldrApplication.class, args);
    }

}
