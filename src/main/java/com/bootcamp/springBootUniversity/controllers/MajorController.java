package com.bootcamp.springBootUniversity.controllers;

import com.bootcamp.springBootUniversity.models.ApiResponse;
import com.bootcamp.springBootUniversity.models.Major;
import com.bootcamp.springBootUniversity.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/majors")
public class MajorController {

    @Autowired
    private MajorService majorService;

    @GetMapping("")
    private ResponseEntity<ApiResponse> getAllMajors() {
        List<Major> major = majorService.getAllMajor();
        ApiResponse response = new ApiResponse(majorService.getResponseMessage(), major);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("")
    private ResponseEntity<ApiResponse> createNewMajor(@RequestBody Major majors) {
        List<Major> major = majorService.addMajor(majorService.getMajors(), majors.getNameMajor());
        ApiResponse response = new ApiResponse(majorService.getResponseMessage(), major);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{majorId}")
    private ResponseEntity<ApiResponse> updateMajor(@PathVariable("majorId") short majorId, @RequestBody Major majors) {
        List<Major> major = majorService.updateMajor(majorId, majors.getNameMajor());
        ApiResponse response = new ApiResponse(majorService.getResponseMessage(), major);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
