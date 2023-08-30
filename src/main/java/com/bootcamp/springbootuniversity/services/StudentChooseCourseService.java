package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.StudentChooseCourse;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentChooseCourseService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Utility utility;

    private static final List<StudentChooseCourse> studentCourse = new ArrayList<>();

    public List<StudentChooseCourse> getStudentCourse() {
        return studentCourse;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public StudentChooseCourse studentChooseCourses(List<StudentChooseCourse> studentCourse, long studentId, long courseId) {
        StudentChooseCourse result = null;
        long studentCourseId = studentCourse.size() + 1;
        if (studentService.studentExists(studentId)) {
            if (courseService.courseExists(courseId)) {
                studentCourse.add(new StudentChooseCourse(studentCourseId, studentId, courseId));
                result = getStudentCourse().get((int) (studentCourseId-1));
                responseMessage = "Students successfully selected course.";
            } else {
                responseMessage = "Sorry, id course doesn't already exists.";
            }
        } else {
            responseMessage = "Sorry, id student doesn't already exists.";
        }
        return result;
    }

    public List<StudentChooseCourse> getAllStudentCourse() {
        if (getStudentCourse().size() == 0) {
            responseMessage = "Data doesn't exists, please insert new data.";
        } else {
            responseMessage = null;
        }
        return getStudentCourse();
    }

    public StudentChooseCourse updateStudentCourse(long studentCourseId, long studentId, long courseId) {
        StudentChooseCourse studentChooseCourse = null;
        if (!studentCourseExists(studentCourseId)) {
            responseMessage = "Sorry, id student course not found.";
        } else if (!studentService.studentExists(studentId)) {
            responseMessage = "Sorry, id student doesn't already exists.";
        } else if (!courseService.courseExists(courseId)) {
            responseMessage = "Sorry, id course doesn't already exists.";
        } else {
            getStudentCourse().get((int) (studentCourseId-1)).setStudentId(studentId);
            getStudentCourse().get((int) (studentCourseId-1)).setCourseId(courseId);
            studentChooseCourse = getStudentCourse().get((int) (studentCourseId-1));
            responseMessage = "Data successfully updated!";
        }
        return studentChooseCourse;
    }

    public StudentChooseCourse inputStudentGrades(long studentCourseId, Integer quiz1, Integer quiz2, Integer quiz3, Integer exam1, Integer exam2) {
        StudentChooseCourse result = null;
        if (!studentCourseExists(studentCourseId)) {
            responseMessage = "Sorry, id student course not found.";
        } else if (utility.gradeCheck(quiz1) == 1 || utility.gradeCheck(quiz2) == 1 || utility.gradeCheck(quiz3) == 1 || utility.gradeCheck(exam1) == 1 || utility.gradeCheck(exam2) == 1) {
            responseMessage = "Sorry, the grades should be between 0-100";
        } else {
            getStudentCourse().get((int) (studentCourseId-1)).setQuiz1(quiz1);
            getStudentCourse().get((int) (studentCourseId-1)).setQuiz2(quiz2);
            getStudentCourse().get((int) (studentCourseId-1)).setQuiz3(quiz3);
            getStudentCourse().get((int) (studentCourseId-1)).setExam1(exam1);
            getStudentCourse().get((int) (studentCourseId-1)).setExam2(exam2);
            result = getStudentCourse().get((int) (studentCourseId-1));
            responseMessage = "Grade entered successfully!";
        }
        return result;
    }

    private boolean studentCourseExists(long studentCourseId) {
        boolean studentCourseIdExists = false;
        int number = 0;
        while (!studentCourseIdExists && number < getStudentCourse().size()) {
            if (getStudentCourse().get(number).getStudentCourseId() == studentCourseId) {
                studentCourseIdExists = true;
            }
        }
        return studentCourseIdExists;
    }
}
