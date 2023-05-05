package com.example.homework9;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Define a custom adapter for the RecyclerView
public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder> implements View.OnClickListener  {
    private List<Post> mPosts;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public GridLayout imageGrid;
        public ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
        public TextView favoriteCount, likeCount, commentCount, title, author, date, description;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);

            imageGrid = itemView.findViewById(R.id.image_grid);
            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
            image4 = itemView.findViewById(R.id.image4);
            image5 = itemView.findViewById(R.id.image5);
            image6 = itemView.findViewById(R.id.image6);
            image7 = itemView.findViewById(R.id.image7);
            image8 = itemView.findViewById(R.id.image8);
            image9 = itemView.findViewById(R.id.image9);
            favoriteCount = itemView.findViewById(R.id.favorite_count);
            likeCount = itemView.findViewById(R.id.like_count);
            commentCount = itemView.findViewById(R.id.comment_count);
        }
    }


    public PostRecyclerViewAdapter(List<Post> posts) {
        mPosts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_cardview_widget, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Log.DEBUG("DEBUG", Integer.toString(mPosts.get(position).getNumImages()));
        Log.d("DEBUG",  Integer.toString(mPosts.get(position).getNumImages()));
        holder.image1.setImageResource(mPosts.get(position).getImageId(0));
        holder.image2.setImageResource(mPosts.get(position).getImageId(1));
        holder.image3.setImageResource(mPosts.get(position).getImageId(2));
        holder.image4.setImageResource(mPosts.get(position).getImageId(3));
        holder.image5.setImageResource(mPosts.get(position).getImageId(4));


        // Only set images if enough, otherwise invisible grid
        if (mPosts.get(position).getNumImages() >= 6) {
            holder.image6.setImageResource(mPosts.get(position).getImageId(5));
        } else {
            holder.image6.setVisibility(View.GONE);
        }

        if (mPosts.get(position).getNumImages() >= 7) {
            holder.image7.setImageResource(mPosts.get(position).getImageId(6));
        } else {
            holder.image7.setVisibility(View.GONE);
        }

        if (mPosts.get(position).getNumImages() >= 8) {
            holder.image8.setImageResource(mPosts.get(position).getImageId(7));
        } else {
            holder.image8.setVisibility(View.GONE);
        }

        if (mPosts.get(position).getNumImages() >= 9) {
            holder.image9.setImageResource(mPosts.get(position).getImageId(8));
        } else {
            holder.image9.setVisibility(View.GONE);
        }


        // Bind other data as needed (likes, favorites, comments)
        holder.favoriteCount.setText(String.valueOf(mPosts.get(position).getFavoriteCount()));
        holder.likeCount.setText(String.valueOf(mPosts.get(position).getLikeCount()));
        holder.commentCount.setText(String.valueOf(mPosts.get(position).getCommentCount()));

        // Title, description, author, date
        holder.title.setText(String.valueOf((mPosts).get(position).getTitle()));
        holder.description.setText(String.valueOf((mPosts).get(position).getDescription()));
        holder.author.setText(String.valueOf((mPosts).get(position).getAuthor()));
        holder.date.setText(String.valueOf((mPosts).get(position).getDate()));

        // When recycler view item is clicked
        Post myObject = mPosts.get(position);
//        holder.bind(myObject);
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(myObject);
    }
    @Override
    public void onClick(View view) {
        // Retrieve the object from the tag of the clicked view
        Post myObject = (Post) view.getTag();

        // Create an intent to launch the DetailActivity
        Intent intent = new Intent(view.getContext(), PostActivity.class);

        // Put the object into the Intent as an extra
        intent.putExtra("Post", myObject);

        // Launch the DetailActivity
        view.getContext().startActivity(intent);
    }


    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}