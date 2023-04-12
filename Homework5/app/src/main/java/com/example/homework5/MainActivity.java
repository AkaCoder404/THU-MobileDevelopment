package com.example.homework5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] items = new String[]{"Item 1", "Item 2"};
    private static final String LIST_ITEMS_KEY = "list_items_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore the list items from the saved instance state if available
        if (savedInstanceState != null && savedInstanceState.containsKey(LIST_ITEMS_KEY)) {
            items = savedInstanceState.getStringArray(LIST_ITEMS_KEY);
        }

        listView = findViewById(R.id.list_view);

        // Create an array of items for the list
        // String[] items = new String[]{"Item 1", "Item 2", "Item 3", "Item 4"};

        // Create an ArrayAdapter to manage the list items
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_item, R.id.list_item_text, items);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set click listener for list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the text of the clicked item
                String clickedItemText = items[position];

                // Create an Intent to start the DetailActivity and pass the clicked item text as extra data
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("clickedItemText", clickedItemText);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArray(LIST_ITEMS_KEY, items);
        super.onSaveInstanceState(outState);
    }
}