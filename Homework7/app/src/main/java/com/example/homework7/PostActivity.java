package com.example.homework7;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class PostActivity extends AppCompatActivity {
    public GridLayout imageGrid;
    public ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    public TextView favoriteCount, likeCount, commentCount, title, author, date, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
//        setContentView(R.layout.post_detail_toolbar);

        // Custom app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                Log.d("DEBUG", "onClick: ");
                finish();
            }
        });

        // Get intent
        Intent intent = getIntent();
        Post mPost = (Post) intent.getSerializableExtra("Post");
//        Log.d("DEBUG", mPost.getAuthor());

        // Set the variables
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        author = findViewById(R.id.author);
        description = findViewById(R.id.description);

        imageGrid = findViewById(R.id.image_grid);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);
        favoriteCount = findViewById(R.id.favorite_count);
        likeCount = findViewById(R.id.like_count);
        commentCount = findViewById(R.id.comment_count);

        title.setText(mPost.getTitle());
        date.setText(mPost.getDate());
        author.setText(mPost.getAuthor());
        description.setText(mPost.getDescription());

        image1.setImageResource(mPost.getImageId(0));
        image2.setImageResource(mPost.getImageId(1));
        image3.setImageResource(mPost.getImageId(2));
        image4.setImageResource(mPost.getImageId(3));
        image5.setImageResource(mPost.getImageId(4));

        if (mPost.getNumImages() > 5) image6.setImageResource(mPost.getImageId(5));
        else image6.setVisibility(View.GONE);

        if (mPost.getNumImages() > 6) image7.setImageResource(mPost.getImageId(6));
        else image7.setVisibility(View.GONE);

        if (mPost.getNumImages() > 7) image8.setImageResource(mPost.getImageId(7));
        else image8.setVisibility(View.GONE);

        if (mPost.getNumImages( )> 8) image9.setImageResource(mPost.getImageId(8));
        else image9.setVisibility(View.GONE);
    }
}