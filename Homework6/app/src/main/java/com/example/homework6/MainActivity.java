package com.example.homework6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private PostRecyclerViewAdapter mAdapter;
    private List<Post> mPosts;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Set an onClickListener to open the new activity
        FloatingActionButton fabCreatePost = findViewById(R.id.fab_create_post);
        fabCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
                startActivityForResult(intent, Constants.REQUEST_CODE_CREATE_ACTIVITY);
            }
        });

        // Initialize posts
        mPosts = new ArrayList<>();
        List<String> tempImages = new ArrayList<>();
        int[] imageIds = { R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
        };


        mPosts.add(new Post("ltq18", "Hello 4", "This is Fourth Post" , imageIds));
        mPosts.add(new Post("ltq18", "Hello 3", "This is Third Post" , imageIds));
        mPosts.add(new Post("ltq18", "Hello 2", "This is Second Post" , imageIds));
        mPosts.add(new Post("ltq18", "Hello 1", "This is First Post", imageIds));


        // Recycler View
        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter and set it on the RecyclerView
        mAdapter = new PostRecyclerViewAdapter(mPosts);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_CODE_CREATE_ACTIVITY && resultCode == RESULT_OK) {
            // replace REQUEST_CODE with the code used to start the child activity
            Post mPost = (Post) data.getSerializableExtra("POST_RESULT"); // get the data sent back
            // do something with the received data
            Log.d("DEBUG", "Back to main from create");
            mPosts.add(0, mPost);
            mAdapter.notifyItemInserted(0);
            mRecyclerView.getLayoutManager().scrollToPosition(0);
        }
    }
}