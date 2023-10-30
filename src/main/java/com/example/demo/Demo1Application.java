package com.example.demo;

import com.example.demo.entity.Field;
import com.example.demo.entity.FieldDTO;
import com.example.demo.service.FieldService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Demo1Application.class, args);
    }

}
