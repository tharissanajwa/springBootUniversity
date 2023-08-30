package com.bootcamp.springBootUniversity.models;

public class StudentChooseCourse {
    private short studentCourseId;
    private short studentId;
    private short courseId;
    private byte quiz1 = -1;
    private byte quiz2 = -1;
    private byte quiz3 = -1;
    private byte exam1 = -1;
    private byte exam2 = -1;

    public StudentChooseCourse(short studentCourseId, short studentId, short courseId) {
        this.studentCourseId = studentCourseId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public short getStudentCourseId() {
        return studentCourseId;
    }

    public short getStudentId() {
        return studentId;
    }

    public void setStudentId(short studentId) {
        this.studentId = studentId;
    }

    public short getCourseId() {
        return courseId;
    }

    public void setCourseId(short courseId) {
        this.courseId = courseId;
    }

    public byte getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(byte quiz1) {
        this.quiz1 = quiz1;
    }

    public byte getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(byte quiz2) {
        this.quiz2 = quiz2;
    }

    public byte getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(byte quiz3) {
        this.quiz3 = quiz3;
    }

    public byte getExam1() {
        return exam1;
    }

    public void setExam1(byte exam1) {
        this.exam1 = exam1;
    }

    public byte getExam2() {
        return exam2;
    }

    public void setExam2(byte exam2) {
        this.exam2 = exam2;
    }
}
