package com.bootcamp.springbootuniversity.models;

// Ini adalah kelas Major yang merepresentasikan data jurusan universitas
public class Major {
    private long majorId; // Id jurusan
    private String nameMajor; // Nama jurusan

    // Konstruktor untuk membuat objek jurusan
    public Major(long majorId, String nameMajor) {
        this.majorId = majorId;
        this.nameMajor = nameMajor;
    }

    // Metode getter setter untuk field-field yg dibutuhkan
    public long getMajorId() {
        return majorId;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }
}
