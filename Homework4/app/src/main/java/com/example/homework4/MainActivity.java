package com.example.homework4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TodoListAdapter adapter;
    private Button addButton;
    private TextInputLayout textInputLayout;
    private TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButton = findViewById(R.id.add_button);
        textInputLayout = findViewById(R.id.textInputLayout);
        recyclerView = findViewById(R.id.recyclerview);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        // Create an adapter and supply the data to be displayed.
        adapter = new TodoListAdapter(this, todoViewModel);
        // Connect the adapter with the recycler view.
        recyclerView.setAdapter(adapter);
        // Give the recycler view a default layout manager.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoViewModel.getAllTodos().observe(this, todos -> {
            adapter.notifyDataSetChanged();
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = textInputLayout.getEditText().getText().toString();
                LiveData<List<Todo>> liveTodoList = todoViewModel.getAllTodos();
                List<Todo> todoList = liveTodoList.getValue();
                int number;
                if (todoList.isEmpty()) {
                    number = 1;
                }else {
//                    number = todoList.get(todoList.size() - 1).getNumber() + 1;
                    number = todoList.get(0).getNumber() + 1;
                }

                Date currentTime = Calendar.getInstance().getTime();
//                String currentTimeString = currentTime.toString();
                String currentTimeString = currentTime.toString().split("GMT")[0];
                Log.d("CREATION", "new todo being created at " + currentTime.toString());
                Todo todo = new Todo(number, content, currentTimeString);
                todoViewModel.insert(todo);
                textInputLayout.getEditText().setText("");
            }
        });
    }
}
