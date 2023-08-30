package com.bootcamp.springBootUniversity.utilities;

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

    public short cekInput(String input) {
        byte valid = 0;
        if (input == null) {
            valid = 1;
        } else if (input.isEmpty()) {
            valid = 2;
        } else if (!input.matches("^[a-zA-Z\\s]+$")) {
            valid = 3;
        }
        return valid;
    }
    
}
