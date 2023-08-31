package com.bootcamp.springbootuniversity.controllers;

import com.bootcamp.springbootuniversity.models.ApiResponse;
import com.bootcamp.springbootuniversity.models.Course;
import com.bootcamp.springbootuniversity.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Kelas ini bertindak sebagai controller untuk mengatur permintaan terkait matkul
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService; // Layanan untuk mengambil data matkul

    // Metode untuk mengambil semua data matkul dari fungsi yg telah dibuat di service
    @GetMapping("")
    private ResponseEntity<ApiResponse> getAllCourse() {
        List<Course> courses = courseService.getAllCourse();
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(), courses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Metode untuk membuat matkul baru dari fungsi yg telah dibuat di service
    @PostMapping
    private ResponseEntity<ApiResponse> createNewCourse(@RequestBody Course course) {
        Course courses = courseService.addCourse(courseService.getCourses(), course.getCourseName());
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(), courses);
        if (courses != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Metode untuk memperbarui informasi matkul dari fungsi yg telah dibuat di service
    @PutMapping("/{courseId}")
    private ResponseEntity<ApiResponse> updateCourse(@PathVariable("courseId") short courseId, @RequestBody Course course) {
        Course courses = courseService.updateCourse(courseId, course.getCourseName());
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(), courses);
        if (courses != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Metode untuk menghapus matkul berdasarkan id dari fungsi yg telah dibuat di service
    @DeleteMapping("/{courseId}")
    private ResponseEntity<ApiResponse> disableCourse(@PathVariable("courseId") short courseId) {
        boolean courses = courseService.disableCourse(courseId);
        ApiResponse response = new ApiResponse(courseService.getResponseMessage(),null);
        if (courses) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
