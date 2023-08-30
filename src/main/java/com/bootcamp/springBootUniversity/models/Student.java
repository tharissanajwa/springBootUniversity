package com.bootcamp.springBootUniversity.models;

public class Student {

    private short studentId;
    private String studentName;
    private short majorId;
    private String majorName;
    private boolean studentStatus;

    public Student(short studentId, String studentName, short majorId, boolean studentStatus) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.majorId = majorId;
        this.studentStatus = studentStatus;
    }

    public short getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public short getMajorId() {
        return majorId;
    }

    public void setMajorId(short majorId) {
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