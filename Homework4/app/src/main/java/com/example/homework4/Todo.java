package com.example.homework4;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class Todo {
    @PrimaryKey
    private int number = 0;
    private String content = "";
    private String date = "";

    public Todo(int number, String content, String date) {
        this.number = number;
        this.content = content;
        this.date = date;
    }


    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }

    public String getDate() { return date; }
}
