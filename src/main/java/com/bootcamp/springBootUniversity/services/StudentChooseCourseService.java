package com.bootcamp.springBootUniversity.services;

import com.bootcamp.springBootUniversity.models.StudentChooseCourse;
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

    public List<StudentChooseCourse> inputStudentGrades(short studentCourseId, byte quiz1, byte quiz2, byte quiz3, byte exam1, byte exam2) {
        List<StudentChooseCourse> result = new ArrayList<>();
        if (studentCourseExists(studentCourseId)) {
            getStudentCourse().get(studentCourseId-1).setQuiz1(quiz1);
            getStudentCourse().get(studentCourseId-1).setQuiz2(quiz2);
            getStudentCourse().get(studentCourseId-1).setQuiz3(quiz3);
            getStudentCourse().get(studentCourseId-1).setExam1(exam1);
            getStudentCourse().get(studentCourseId-1).setExam2(exam2);
            StudentChooseCourse studentCourse = getStudentCourse().get(studentCourseId-1);
            result.add(studentCourse);
            responseMessage = "Grade entered successfully!";
        } else {
            responseMessage = "Sorry, id student course not found.";
        }
        return result;
    }
}
