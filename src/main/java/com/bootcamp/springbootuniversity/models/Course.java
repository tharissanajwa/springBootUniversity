package com.bootcamp.springbootuniversity.models;

public class Course {

    private long courseId;
    private String courseName;
    private boolean courseStatus;

    public Course(long courseId, String courseName, boolean courseStatus) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
    }

    public long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(boolean courseStatus) {
        this.courseStatus = courseStatus;
    }

}
