package com.bootcamp.springbootuniversity.models;

// Ini adalah kelas Course yang merepresentasikan data mata kuliah.
public class Course {
    private long courseId; // Id matkul
    private String courseName; // Nama matkul
    private boolean courseStatus; // Status matkul, true bila aktif dan false bila non aktif

    // Konstruktor untuk membuat objek matkul
    public Course(long courseId, String courseName, boolean courseStatus) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseStatus = courseStatus;
    }

    // Metode getter setter untuk field-field yg dibutuhkan
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
