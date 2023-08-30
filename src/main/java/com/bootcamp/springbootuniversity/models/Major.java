package com.bootcamp.springbootuniversity.models;

public class Major {

    private long majorId;
    private String nameMajor;

    public Major(long majorId, String nameMajor) {
        this.majorId = majorId;
        this.nameMajor = nameMajor;
    }

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
