package com.example.homework6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    }
}