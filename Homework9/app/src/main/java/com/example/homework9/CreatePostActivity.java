package com.example.homework9;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CreatePostActivity extends AppCompatActivity {
    private EditText title;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);


        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Create Post");

        title = findViewById(R.id.title_edittext);
        description = findViewById(R.id.description_edittext);

        // Load the saved text values from SharedPreferences
//        SharedPreferences prefs = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        String title = prefs.getString("title", "");
//        String description = prefs.getString("description", "");

        // Set an onClickListener to handle publishing action
        Button btnPublish = findViewById(R.id.btn_publish);
        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to create a new post object and save it to a data source
                Log.d("DEBUG", "Create posts finished");

                // TODO: Temporary photos auto upload
                int[] mImages = { R.drawable.testphoto1,
                        R.drawable.testphoto1,
                        R.drawable.testphoto1,
                        R.drawable.testphoto1,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                        R.drawable.ic_launcher_foreground,
                };
                Post mPost = new Post("litq18", title.getText().toString(), description.getText().toString(), mImages);

                // Send intent back to home activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("POST_RESULT", mPost); // add any data you want to send back
                setResult(Activity.RESULT_OK, resultIntent);

                finish(); // Close the activity after publishing
            }
        });

        // Set onclick listener for save
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to create a new post object and save it to a data source
                // Save the text values to SharedPreferences
                SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("title", title.getText().toString());
                editor.putString("description", description.getText().toString());
                editor.apply(); // Commit the changes asynchronously
            }});
    }


}