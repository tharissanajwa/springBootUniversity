package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.Major;
import com.bootcamp.springbootuniversity.models.Student;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private MajorService majorService;

    @Autowired
    private Utility utility;

    private static final List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public Student addStudent(List<Student> students, String name, long majorId) {
        Student result = null;
        long studentId = students.size() + 1;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, student name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, student name can only filled by letters";
        } else {
            if (majorService.majorExists(majorId)) {
                students.add(new Student(studentId, utility.inputTrim(name), majorId, true));
                result = getStudents().get((int) (studentId-1));
                responseMessage = "Data successfully added!";
            } else {
                responseMessage = "Sorry, id major doesn't already exists.";
            }
        }
        return result;
    }

    public List<Student> getAllStudent() {
        List<Student> result = new ArrayList<>();

        for (Student student : getStudents()) {
            if (student.getStudentStatus()) {
                String majorName = getMajorName(student.getMajorId());
                student.setMajorName(majorName);
                result.add(student);
            }
        }

        if (result.isEmpty()) {
            responseMessage = "Data doesn't exists, please insert new data student.";
        } else {
            responseMessage = null;
        }

        return result;
    }

    private String getMajorName(long majorId) {
        String nameMajor = "";
        for (Major major : majorService.getMajors()) {
            if (majorId == major.getMajorId()) {
                nameMajor = major.getNameMajor();
            }
        }
        return nameMajor;
    }

    public Student updateStudent(long studentId, String name, long majorId) {
        Student result = null;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, student name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, student name can only filled by letters";
        } else {
            if (studentExists(studentId) && getStudents().get((int) (studentId-1)).getStudentStatus()) {
                if (majorService.majorExists(majorId)) {
                    getStudents().get((int) (studentId - 1)).setStudentName(utility.inputTrim(name));
                    getStudents().get((int) (studentId - 1)).setMajorId(majorId);
                    String majorName = getMajorName(majorId);
                    getStudents().get((int) (studentId - 1)).setMajorName(majorName);
                    result = getStudents().get((int) (studentId - 1));
                    responseMessage = "Data successfully updated!";
                } else {
                    responseMessage = "Sorry, id major doesn't already exists.";
                }
            } else {
                responseMessage = "Sorry, id student is not found.";
            }
        }
        return result;
    }

    public boolean disableStudent(long studentId) {
        boolean result = false;
        if (studentExists(studentId) && getStudents().get((int) (studentId-1)).getStudentStatus()) {
            getStudents().get((int) (studentId-1)).setStudentStatus(false);
            result = true;
            responseMessage = "Data deactivated successfully!";
        } else {
            responseMessage = "Sorry, id student is not found.";
        }
        return result;
    }

    public boolean studentExists(long studentId) {
        boolean studentExists = false;
        int number = 0;
        while (!studentExists && number < getStudents().size()) {
            if (getStudents().get(number).getStudentStatus() && getStudents().get(number).getStudentId() == studentId) {
                studentExists = true;
            }
            number++;
        }
        return studentExists;
    }
}
