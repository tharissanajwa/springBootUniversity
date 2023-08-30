package com.bootcamp.springbootuniversity.models;

public class Student {

    private long studentId;
    private String studentName;
    private long majorId;
    private String majorName;
    private boolean studentStatus;

    public Student(long studentId, String studentName, long majorId, boolean studentStatus) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.majorId = majorId;
        this.studentStatus = studentStatus;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }

    public boolean getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(boolean studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}