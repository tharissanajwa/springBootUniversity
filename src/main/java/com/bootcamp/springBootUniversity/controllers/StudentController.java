package com.bootcamp.springBootUniversity.controllers;

import com.bootcamp.springBootUniversity.models.ApiResponse;
import com.bootcamp.springBootUniversity.models.Course;
import com.bootcamp.springBootUniversity.models.Student;
import com.bootcamp.springBootUniversity.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping("")
    private ResponseEntity<ApiResponse> getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    private ResponseEntity<ApiResponse> createNewStudent(@RequestBody Student student) {
        List<Student> students = studentService.addStudent(studentService.getStudents(), student.getStudentName(), student.getMajorId());
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{studentId}")
    private ResponseEntity<ApiResponse> updateStudent(@PathVariable("studentId") short studentId, @RequestBody Student student) {
        List<Student> students = studentService.updateStudent(studentId, student.getStudentName(), student.getMajorId());
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{studentId}")
    private ResponseEntity<ApiResponse> disableStudent(@PathVariable("studentId") short studentId) {
        List<Student> students = studentService.disableStudent(studentId);
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
