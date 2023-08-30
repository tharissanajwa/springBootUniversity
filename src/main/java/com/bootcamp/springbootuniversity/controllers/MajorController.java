package com.bootcamp.springbootuniversity.controllers;

import com.bootcamp.springbootuniversity.models.ApiResponse;
import com.bootcamp.springbootuniversity.models.Major;
import com.bootcamp.springbootuniversity.services.MajorService;
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
        Major major = majorService.addMajor(majorService.getMajors(), majors.getNameMajor());
        ApiResponse response = new ApiResponse(majorService.getResponseMessage(), major);
        if (major != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{majorId}")
    private ResponseEntity<ApiResponse> updateMajor(@PathVariable("majorId") short majorId, @RequestBody Major majors) {
        Major major = majorService.updateMajor(majorId, majors.getNameMajor());
        ApiResponse response = new ApiResponse(majorService.getResponseMessage(), major);
        if (major != null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
