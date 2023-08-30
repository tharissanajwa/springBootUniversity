package com.bootcamp.springBootUniversity.services;

import com.bootcamp.springBootUniversity.models.Major;
import com.bootcamp.springBootUniversity.models.Student;
import com.bootcamp.springBootUniversity.utilities.Utility;
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

    private List<Student> students = new ArrayList<>();

    public List<Student> getStudents() {
        return students;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public List<Student> addStudent(List<Student> students, String name, short majorId) {
        short studentId = (short) (students.size() + 1);
        short cekInput = utility.cekInput(utility.inputTrim(name));

        if (cekInput == 1) {
            responseMessage = "Sorry, student name cannot be null.";
        } else if (cekInput == 2) {
            responseMessage = "Sorry, student name cannot be empty";
        } else if (cekInput == 3) {
            responseMessage = "Sorry, student name can only filled by letters";
        } else {
            if (majorService.majorExists(majorId)) {
                students.add(new Student(studentId, utility.inputTrim(name), majorId, true));
                responseMessage = "Data successfully added!";
            } else {
                responseMessage = "Sorry, id major doesn't already exists.";
            }
        }
        return students;
    }

    public boolean studentExists(short studentId) {
        boolean studentExists = false;
        for (Student student: getStudents()) {
            if (studentId == student.getStudentId()) {
                studentExists = true;
                break;
            }
        }
        return studentExists;
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

    private String getMajorName(short majorId) {
        String nameMajor = "";
        for (Major major : majorService.getMajors()) {
            if (majorId == major.getMajorId()) {
                nameMajor = major.getNameMajor();
            }
        }
        return nameMajor;
    }

    public List<Student> updateStudent(short studentId, String name, short majorId) {
        List<Student> result = new ArrayList<>();
        short cekInput = utility.cekInput(utility.inputTrim(name));

        if (cekInput == 1) {
            responseMessage = "Sorry, student name cannot be null.";
        } else if (cekInput == 2) {
            responseMessage = "Sorry, student name cannot be empty";
        } else if (cekInput == 3) {
            responseMessage = "Sorry, student name can only filled by letters";
        } else {
            if (studentExists(studentId) && getStudents().get(studentId-1).getStudentStatus()) {
                if (majorService.majorExists(majorId)) {
                    getStudents().get(studentId - 1).setStudentName(utility.inputTrim(name));
                    getStudents().get(studentId - 1).setMajorId(majorId);
                    String majorName = getMajorName(majorId);
                    getStudents().get(studentId - 1).setMajorName(majorName);
                    Student student = getStudents().get(studentId - 1);
                    result.add(student);
                    responseMessage = "Data successfully updated!";
                }
            } else {
                responseMessage = "Sorry, id student is not found.";
            }
        }
        return result;
    }

    public List<Student> disableStudent(short studentId) {
        List<Student> result = new ArrayList<>();

        if (studentExists(studentId) && getStudents().get(studentId-1).getStudentStatus()) {
            getStudents().get(studentId-1).setStudentStatus(false);
            Student student = getStudents().get(studentId-1);
            result.add(student);
            responseMessage = "Data deactivated successfully!";
        } else {
            responseMessage = "Sorry, id student is not found.";
        }
        return result;
    }
}
