package com.bootcamp.springBootUniversity.models;

public class Major {

    private short majorId;
    private String nameMajor;

    public Major(short majorId, String nameMajor) {
        this.majorId = majorId;
        this.nameMajor = nameMajor;
    }

    public short getMajorId() {
        return majorId;
    }

    public String getNameMajor() {
        return nameMajor;
    }

    public void setNameMajor(String nameMajor) {
        this.nameMajor = nameMajor;
    }
}
