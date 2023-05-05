package com.example.homework9;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

// Create a model class for the post object
public class Post implements Parcelable {
    private int id;
    private String author;
    private String title;
    private String description;
    private Date date;
    private int likeCount;
    private int commentsCount;
    private int favoriteCount;
    private int[] imageIds;

    public Post(String author, String title, String description, int[] imageIds) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.imageIds = imageIds;

        this.date = new Date();
        this.likeCount = 0;
        this.commentsCount = 0;
        this.favoriteCount = 0;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date.toString();
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentsCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }


    public int getImageId(int index) {
        return imageIds[index];
    }

    public int getNumImages() { return imageIds.length; }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(date.toString());
        dest.writeInt(likeCount);
        dest.writeInt(commentsCount);
        dest.writeInt(favoriteCount);
        dest.writeIntArray(imageIds);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Post(Parcel in) {
        id = in.readInt();
        author = in.readString();
        title = in.readString();
        description = in.readString();

        try {
            SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
            date = format.parse(in.readString()); // convert the string to a Date object
        } catch (Exception e) {

        }

        likeCount = in.readInt();
        commentsCount = in.readInt();
        favoriteCount = in.readInt();
        imageIds = in.createIntArray();
    }
}
