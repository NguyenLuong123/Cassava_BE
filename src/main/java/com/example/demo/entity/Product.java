package com.example.demo.entity;

import javax.annotation.Nullable;

public class Product {
    @Nullable
    private String name;

    private String decription;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

}
