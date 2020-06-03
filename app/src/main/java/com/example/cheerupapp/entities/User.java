package com.example.cheerupapp.entities;

import java.io.Serializable;

public class User implements Serializable {

    // this USER constant is used to identify the user instance to pass between activities
    public static final String USER_KEY = "USER";
    public static final String USER_LEAVE_MESSAGE= "See you again!";
    public static final String USER_DANCE_LEAVE_MESSAGE= "Hope you enjoyed dancing!";

    private String name;
    //private String leavingMessage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

