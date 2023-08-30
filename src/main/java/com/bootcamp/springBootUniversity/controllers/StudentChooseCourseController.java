package com.bootcamp.springBootUniversity.controllers;

import com.bootcamp.springBootUniversity.models.ApiResponse;
import com.bootcamp.springBootUniversity.models.StudentChooseCourse;
import com.bootcamp.springBootUniversity.services.StudentChooseCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-course")
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
        List<StudentChooseCourse> studentChooseCourse = studentCourseService.studentChooseCourses(studentCourseService.getStudentCourse(), studentCourse.getStudentId(), studentCourse.getCourseId());
        ApiResponse response = new ApiResponse(studentCourseService.getResponseMessage(), studentChooseCourse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{studentCourseId}")
    private ResponseEntity<ApiResponse> inputStudentGrades(@PathVariable("studentCourseId") short studentCourseId, @RequestBody StudentChooseCourse studentCourse) {
        List<StudentChooseCourse> studentChooseCourse = studentCourseService.inputStudentGrades(studentCourseId, studentCourse.getQuiz1(), studentCourse.getQuiz2(), studentCourse.getQuiz3(), studentCourse.getExam1(), studentCourse.getExam2());
        ApiResponse response = new ApiResponse(studentCourseService.getResponseMessage(), studentChooseCourse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
