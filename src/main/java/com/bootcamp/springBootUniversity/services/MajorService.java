package com.bootcamp.springBootUniversity.services;

import com.bootcamp.springBootUniversity.models.Major;
import com.bootcamp.springBootUniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorService {

    @Autowired
    private Utility utility;
    private List<Major> majors = new ArrayList<>();

    public List<Major> getMajors () {
        return majors;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public List<Major> addMajor (List<Major> majors, String name) {
        short majorId = (short) (majors.size() + 1);
        byte inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, major name cannot be null.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, major name cannot be empty";
        } else if (inputCheck == 3) {
            responseMessage = "Sorry, major name can only filled by letters";
        } else {
            majors.add(new Major(majorId, utility.inputTrim(name)));
            responseMessage = "Data successfully added!";
        }
        return majors;
    }

    public boolean majorExists(short majorId) {
        boolean majorExists = false;
        for (Major major: getAllMajor()) {
            if (majorId == major.getMajorId()) {
                majorExists = true;
                break;
            }
        }
        return majorExists;
    }

    public List<Major> getAllMajor () {
        List<Major> result = new ArrayList<>();
        for (Major majors: getMajors()) {
            result.add(majors);
        }
        if (result.isEmpty()) {
            responseMessage = "Data not exists, please insert new data major.";
        } else {
            responseMessage = null;
        }
        return result;
    }

    public List<Major> updateMajor(short majorId, String name) {
        List<Major> result = new ArrayList<>();
        byte inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, major name cannot be null.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, major name cannot be empty";
        } else if (inputCheck == 3) {
            responseMessage = "Sorry, major name can only filled by letters";
        } else {
            if (majorExists(majorId)) {
                getAllMajor().get(majorId-1).setNameMajor(utility.inputTrim(name));
                Major major = getAllMajor().get(majorId-1);
                result.add(major);
                responseMessage = "Data successfully updated!";
            } else {
                responseMessage = "ID major not found!";
            }
        }
        return result;
    }
}
