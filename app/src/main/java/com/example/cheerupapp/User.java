package com.example.cheerupapp;

import java.io.Serializable;

public class User implements Serializable {

    // this USER constant is used to identify the user instance to pass between activities
    public static final String USER_KEY = "USER";

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

