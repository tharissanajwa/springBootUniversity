package com.bootcamp.springbootuniversity.models;

// Ini adalah kelas Student yang merepresentasikan data mahasiswa
public class Student {
    private long studentId; // Id mahasiswa
    private String studentName; // Nama mahasiswa
    private long majorId; // Id jurusan sebagai relasi dengan model jurusan
    private String majorName; // Nama jurusan mahasiswa tersebut
    private boolean studentStatus; // Status mahasiswa

    // Konstruktor untuk membuat objek mahasiswa
    public Student(long studentId, String studentName, long majorId, boolean studentStatus) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.majorId = majorId;
        this.studentStatus = studentStatus;
    }

    // Metode getter setter untuk field-field yg dibutuhkan
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