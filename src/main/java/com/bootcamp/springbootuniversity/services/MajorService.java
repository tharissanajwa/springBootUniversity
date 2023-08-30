package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.Major;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MajorService {

    @Autowired
    private Utility utility;
    private static final List<Major> majors = new ArrayList<>();

    public List<Major> getMajors () {
        return majors;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public Major addMajor (List<Major> majors, String name) {
        Major result = null;
        long majorId = majors.size() + 1;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, major name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, major name can only filled by letters";
        } else {
            majors.add(new Major(majorId, utility.inputTrim(name)));
            result = getMajors().get((int) (majorId-1));
            responseMessage = "Data successfully added!";
        }
        return result;
    }

    public List<Major> getAllMajor () {
        if (getMajors().size() == 0) {
            responseMessage = "Data not exists, please insert new data major.";
        } else {
            responseMessage = null;
        }
        return getMajors();
    }

    public Major updateMajor(long majorId, String name) {
        Major result = null;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, major name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, major name can only filled by letters";
        } else {
            if (majorExists(majorId)) {
                getAllMajor().get((int) (majorId-1)).setNameMajor(utility.inputTrim(name));
                result = getAllMajor().get((int) (majorId-1));
                responseMessage = "Data successfully updated!";
            } else {
                responseMessage = "Sorry, id major not found.";
            }
        }
        return result;
    }

    public boolean majorExists(long majorId) {
        boolean majorExists = false;
        int number = 0;
        while (!majorExists && number < getMajors().size()) {
            if (getMajors().get(number).getMajorId() == majorId) {
                majorExists = true;
            }
            number++;
        }
        return majorExists;
    }
}
