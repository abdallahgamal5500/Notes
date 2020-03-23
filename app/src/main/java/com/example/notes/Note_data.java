package com.example.notes;

import java.util.Map;

public class Note_data {

    private String title;
    private String text;
    private String id;
    private Map<String, String> time;

    public Note_data(String title, String text, String id, Map<String, String> time) {
        this.title = title;
        this.text = text;
        this.id = id;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
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

    public Map<String, String> getTime() {
        return time;
    }

    public void setTime(Map<String, String> time) {
        this.time = time;
    }
}
