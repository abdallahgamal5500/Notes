package com.example.notes;

import com.google.android.material.textfield.TextInputLayout;

public class Note_data {

    private String title, text, id, date;

    public Note_data() {
    }

    public Note_data(String title, String text, String id, String date) {
        this.title = title;
        this.text = text;
        this.id = id;
        this.date = date;
    }

    public Note_data(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

