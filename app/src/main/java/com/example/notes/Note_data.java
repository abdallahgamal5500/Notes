package com.example.notes;

import java.util.Map;

public class Note_data {

    private String Title,Text,Id;
    private Map<String, String> Time;

    public Note_data(String title, String text, String id, Map<String, String> time) {
        Title = title;
        Text = text;
        Id = id;
        Time = time;
    }
}
