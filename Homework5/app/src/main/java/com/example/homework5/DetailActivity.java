package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView textView;
    private static final String CLICKED_ITEM_TEXT_KEY = "clicked_item_text_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the clicked item text from the intent
        Intent intent = getIntent();
        String clickedItemText = intent.getStringExtra("clickedItemText");

        // Restore the clicked item text from the saved instance state if available
        if (savedInstanceState != null && savedInstanceState.containsKey(CLICKED_ITEM_TEXT_KEY)) {
            clickedItemText = savedInstanceState.getString(CLICKED_ITEM_TEXT_KEY);
        }

        // Set the clicked item text to the TextView
        textView = findViewById(R.id.detail_text_view);
        textView.setText(clickedItemText);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Save the clicked item text to the saved instance state
        outState.putString(CLICKED_ITEM_TEXT_KEY, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }
}