package com.example.homework7;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeNewPostsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private PostRecyclerViewAdapter mAdapter;
    private List<Post> mPosts;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_new_posts, container, false);

        mPosts = new ArrayList<>();
        List<String> tempImages = new ArrayList<>();
        int[] imageIds = { R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
        };

        int[] imageIds1 = { R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,
                R.drawable.testphoto1,

        };

        mPosts.add(new Post("ltq18", "Hello 4", "This is Fourth Post" , imageIds));
        mPosts.add(new Post("ltq18", "Hello 3", "This is Third Post" , imageIds1));
        mPosts.add(new Post("ltq18", "Hello 2", "This is Second Post" , imageIds));
        mPosts.add(new Post("ltq18", "Hello 1", "This is First Post", imageIds1));

        // Recycler View
        mRecyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        // Initialize the adapter and set it on the RecyclerView
        mAdapter = new PostRecyclerViewAdapter(mPosts);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == Constants.REQUEST_CODE_CREATE_ACTIVITY && resultCode == RESULT_OK) {
//            // replace REQUEST_CODE with the code used to start the child activity
//            Post mPost = (Post) data.getSerializableExtra("POST_RESULT"); // get the data sent back
//            // do something with the received data
//            Log.d("DEBUG", "Back to main from create");
//            mPosts.add(0, mPost);
//            mAdapter.notifyItemInserted(0);
//            mRecyclerView.getLayoutManager().scrollToPosition(0);
//        }
//    }
}