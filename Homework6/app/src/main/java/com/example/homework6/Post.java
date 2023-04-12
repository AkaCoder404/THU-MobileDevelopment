package com.example.homework6;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

// Create a model class for the post object
public class Post implements Serializable {
    private int id;
    private String author;
    private String title;
    private String description;
    private Date date;
    private int likeCount;
    private int commentsCount;
    private int favoriteCount;
    private List<String> imageList;
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
}
