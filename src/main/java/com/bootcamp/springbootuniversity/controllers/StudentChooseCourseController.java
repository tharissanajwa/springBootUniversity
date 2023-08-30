package com.bootcamp.springbootuniversity.controllers;

import com.bootcamp.springbootuniversity.models.ApiResponse;
import com.bootcamp.springbootuniversity.models.StudentChooseCourse;
import com.bootcamp.springbootuniversity.services.StudentChooseCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student-courses")
public class StudentChooseCourseController {

    @Autowired
    private StudentChooseCourseService studentCourseService;

    @GetMapping("")
    private ResponseEntity<ApiResponse> getAllStudentCourse() {
        List<StudentChooseCourse> studentCourse = studentCourseService.getAllStudentCourse();
        ApiResponse response = new ApiResponse(studentCourseService.getResponseMessage(), studentCourse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    private ResponseEntity<ApiResponse> studentChooseCourse(@RequestBody StudentChooseCourse studentCourse) {
        StudentChooseCourse studentChooseCourse = studentCourseService.studentChooseCourses(studentCourseService.getStudentCourse(), studentCourse.getStudentId(), studentCourse.getCourseId());
        ApiResponse response = new ApiResponse(studentCourseService.getResponseMessage(), studentChooseCourse);
        if (studentChooseCourse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{studentCourseId}/grade")
    private ResponseEntity<ApiResponse> inputStudentGrades(@PathVariable("studentCourseId") int studentCourseId, @RequestBody StudentChooseCourse studentCourse) {
        StudentChooseCourse studentChooseCourse = studentCourseService.inputStudentGrades(studentCourseId, studentCourse.getQuiz1(), studentCourse.getQuiz2(), studentCourse.getQuiz3(), studentCourse.getExam1(), studentCourse.getExam2());
        ApiResponse response = new ApiResponse(studentCourseService.getResponseMessage(), studentChooseCourse);
        if (studentChooseCourse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{studentCourseId}")
    private ResponseEntity<ApiResponse> updateStudentCourse(@PathVariable("studentCourseId") int studentCourseId, @RequestBody StudentChooseCourse studentCourse) {
        StudentChooseCourse studentChooseCourse = studentCourseService.updateStudentCourse(studentCourseId, studentCourse.getStudentId(), studentCourse.getCourseId());
        ApiResponse response = new ApiResponse(studentCourseService.getResponseMessage(), studentChooseCourse);
        if (studentChooseCourse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
