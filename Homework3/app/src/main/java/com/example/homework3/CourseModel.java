package com.example.homework3;

import androidx.annotation.NonNull;

public class CourseModel {
    // string course_name for storing course_name
    // and ImgId for storing image id.
    private String course_name;
    private int ImgId;

    public CourseModel(String course_name, int ImgId) {
        this.course_name = course_name;
        this.ImgId = ImgId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getImgId() {
        return ImgId;
    }

    public void setImgId(int ImgId) {
        this.ImgId = ImgId;
    }

}