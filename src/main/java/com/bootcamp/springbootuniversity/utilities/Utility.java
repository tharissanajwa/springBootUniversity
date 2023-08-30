package com.bootcamp.springbootuniversity.utilities;

import org.springframework.stereotype.Service;

@Service
public class Utility {

    public String inputTrim(String input) {
        String inputTrim;
        if (input == null) {
            inputTrim = null;
        } else {
            inputTrim = input.trim().replaceAll("\\s+", " ");
        }
        return inputTrim;
    }

    public int inputCheck(String input) {
        int valid = 0;
        if (input == null || input.isEmpty()) {
            valid = 1;
        } else if (!input.matches("^[a-zA-Z\\s]+$")) {
            valid = 2;
        }
        return valid;
    }

    public int gradeCheck(Integer grade) {
        int valid = 0;
        if (grade != null) {
            if (grade < 0 || grade > 100) {
                valid = 1;
            }
        }
        return valid;
    }
}
