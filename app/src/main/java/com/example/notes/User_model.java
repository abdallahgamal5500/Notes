package com.example.notes;

public class User_model {
    private String full_name;

    public User_model() {
    }

    public User_model(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}
