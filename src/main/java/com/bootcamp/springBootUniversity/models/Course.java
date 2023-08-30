package com.bootcamp.springBootUniversity.models;

public class Course {

    private short courseId;
    private String courseName;
    private boolean courseStatus;

    public Course(short courseId, String courseName, boolean courseStatus) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
    }

    public short getCourseId() {
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
