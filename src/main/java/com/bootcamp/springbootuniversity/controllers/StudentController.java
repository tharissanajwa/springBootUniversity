package com.bootcamp.springbootuniversity.controllers;

import com.bootcamp.springbootuniversity.models.ApiResponse;
import com.bootcamp.springbootuniversity.models.Student;
import com.bootcamp.springbootuniversity.services.StudentService;
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

// Kelas ini bertindak sebagai controller untuk mengatur permintaan terkait mahasiswa
@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService; // Layanan untuk mengambil data mahasiswa

    // Metode untuk mengambil semua data mahasiswa dari fungsi yg telah dibuat di service
    @GetMapping("")
    private ResponseEntity<ApiResponse> getAllStudent() {
        List<Student> students = studentService.getAllStudent();
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), students);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Metode untuk membuat mahasiswa baru dari fungsi yg telah dibuat di service
    @PostMapping("")
    private ResponseEntity<ApiResponse> createNewStudent(@RequestBody Student student) {
        Student students = studentService.addStudent(studentService.getStudents(), student.getStudentName(), student.getMajorId());
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), students);
        if (students != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Metode untuk memperbarui informasi mahasiswa dari fungsi yg telah dibuat di service
    @PutMapping("/{studentId}")
    private ResponseEntity<ApiResponse> updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student) {
        Student students = studentService.updateStudent(studentId, student.getStudentName(), student.getMajorId());
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), students);
        if (students != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Metode untuk menghapus mahasiswa berdasarkan id dari fungsi yg telah dibuat di service
    @DeleteMapping("/{studentId}")
    private ResponseEntity<ApiResponse> disableStudent(@PathVariable("studentId") int studentId) {
        boolean students = studentService.disableStudent(studentId);
        ApiResponse response = new ApiResponse(studentService.getResponseMessage(), null);
        if (students) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
