package com.bootcamp.springBootUniversity.services;

import com.bootcamp.springBootUniversity.models.StudentChooseCourse;
import com.bootcamp.springBootUniversity.utilities.Utility;
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

    private List<StudentChooseCourse> studentCourse = new ArrayList<>();

    public List<StudentChooseCourse> getStudentCourse() {
        return studentCourse;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public List<StudentChooseCourse> studentChooseCourses(List<StudentChooseCourse> studentCourse, short studentId, short courseId) {
        short studentCourseId = (short) (studentCourse.size() + 1);
        if (studentService.studentExists(studentId)) {
            if (courseService.courseExists(courseId)) {
                studentCourse.add(new StudentChooseCourse(studentCourseId, studentId, courseId));
                responseMessage = "Students successfully selected course.";
            } else {
                responseMessage = "Sorry, id course doesn't already exists.";
            }
        } else {
            responseMessage = "Sorry, id student doesn't already exists.";
        }
        return studentCourse;
    }

    private boolean studentCourseExists(short studentCourseId) {
        boolean studentCourseIdExists = false;
        for (StudentChooseCourse studentCourse : getAllStudentCourse()) {
            if (studentCourseId == studentCourse.getStudentCourseId()) {
                studentCourseIdExists = true;
                break;
            }
        }

        return studentCourseIdExists;
    }

    public List<StudentChooseCourse> getAllStudentCourse() {
        List<StudentChooseCourse> result = new ArrayList<>();

        for (StudentChooseCourse studentCourse : getStudentCourse()) {
            result.add(studentCourse);
        }

        if (result.isEmpty()) {
            responseMessage = "Data doesn't exists, please insert new data.";
        } else {
            responseMessage = null;
        }
        return result;
    }

    public List<StudentChooseCourse> inputStudentGrades(short studentCourseId, short studentId, short courseId, byte quiz1, byte quiz2, byte quiz3, byte exam1, byte exam2) {
        List<StudentChooseCourse> result = new ArrayList<>();
        if (!studentCourseExists(studentCourseId)) {
            responseMessage = "Sorry, id student course not found.";
        } else if (utility.gradeCheck(quiz1) == 1 || utility.gradeCheck(quiz2) == 1 || utility.gradeCheck(quiz3) == 1 || utility.gradeCheck(exam1) == 1 || utility.gradeCheck(exam2) == 1) {
            responseMessage = "Sorry, the grades should be between 0-100";
        } else if (!studentService.studentExists(studentId)) {
            responseMessage = "Sorry, id student doesn't already exists.";
        } else if (!courseService.courseExists(courseId)) {
            responseMessage = "Sorry, id course doesn't already exists.";
        } else {
            getStudentCourse().get(studentCourseId-1).setStudentId(studentId);
            getStudentCourse().get(studentCourseId-1).setCourseId(courseId);
            getStudentCourse().get(studentCourseId-1).setQuiz1(quiz1);
            getStudentCourse().get(studentCourseId-1).setQuiz2(quiz2);
            getStudentCourse().get(studentCourseId-1).setQuiz3(quiz3);
            getStudentCourse().get(studentCourseId-1).setExam1(exam1);
            getStudentCourse().get(studentCourseId-1).setExam2(exam2);
            StudentChooseCourse studentCourse = getStudentCourse().get(studentCourseId-1);
            result.add(studentCourse);
            responseMessage = "Grade entered successfully!";
        }
        return result;
    }
}
