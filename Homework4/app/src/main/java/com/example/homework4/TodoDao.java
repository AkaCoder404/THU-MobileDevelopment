package com.example.homework4;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insert(Todo todo);

    @Delete
    void delete(Todo todo);

    @Query("SELECT * from todo_table ORDER BY date DESC")
    LiveData<List<Todo>> getAllTodos();
}
