package com.bootcamp.springbootuniversity.models;

public class StudentChooseCourse {
    private long studentCourseId;
    private long studentId;
    private long courseId;
    private Integer quiz1 = null;
    private Integer quiz2 = null;
    private Integer quiz3 = null;
    private Integer exam1 = null;
    private Integer exam2 = null;

    public StudentChooseCourse(long studentCourseId, long studentId, long courseId) {
        this.studentCourseId = studentCourseId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public long getStudentCourseId() {
        return studentCourseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public Integer getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(Integer quiz1) {
        this.quiz1 = quiz1;
    }

    public Integer getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(Integer quiz2) {
        this.quiz2 = quiz2;
    }

    public Integer getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(Integer quiz3) {
        this.quiz3 = quiz3;
    }

    public Integer getExam1() {
        return exam1;
    }

    public void setExam1(Integer exam1) {
        this.exam1 = exam1;
    }

    public Integer getExam2() {
        return exam2;
    }

    public void setExam2(Integer exam2) {
        this.exam2 = exam2;
    }
}
