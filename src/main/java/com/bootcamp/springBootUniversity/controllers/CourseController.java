package com.bootcamp.springBootUniversity.controllers;

import com.bootcamp.springBootUniversity.models.ApiResponse;
import com.bootcamp.springBootUniversity.models.Course;
import com.bootcamp.springBootUniversity.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    private ResponseEntity<ApiResponse> getAllCourse() {
        List<Course> courses = courseService.getAllCourse();
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(), courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    private ResponseEntity<ApiResponse> createNewCourse(@RequestBody Course course) {
        List<Course> courses = courseService.addCourse(courseService.getCourses(), course.getCourseName());
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(), courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{courseId}")
    private ResponseEntity<ApiResponse> updateCourse(@PathVariable("courseId") short courseId, @RequestBody Course course) {
        List<Course> courses = courseService.updateCourse(courseId, course.getCourseName());
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(), courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{courseId}")
    private ResponseEntity<ApiResponse> disableCourse(@PathVariable("courseId") short courseId) {
        List<Course> courses = courseService.disableCourse(courseId);
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(), courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
