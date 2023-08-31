package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.Major;
import com.bootcamp.springbootuniversity.models.Student;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Kelas ini bertanggung jawab untuk mengelola data mahasiswa
@Service
public class StudentService {

    @Autowired
    private MajorService majorService;

    @Autowired
    private Utility utility;

    private static final List<Student> students = new ArrayList<>(); // List untuk menyimpan data mahasiswa

    // Metode untuk mendapatkan daftar data mahasiswa
    public List<Student> getStudents() {
        return students;
    }

    private String responseMessage; // Pesan status untuk memberi informasi kepada pengguna

    // Metode untuk mendapatkan pesan status
    public String getResponseMessage() {
        return responseMessage;
    }

    // Metode untuk menambahkan mahasiswa baru ke dalam daftar mahasiswa
    public Student addStudent(List<Student> students, String name, long majorId) {
        Student result = null;
        long studentId = students.size() + 1; // Fungsinya untuk menambahkan id mahasiswa secara otomatis
        int inputCheck = utility.inputCheck(utility.inputTrim(name)); // Fungsinya sebagai validasi dari nama yg diinputkan pengguna

        if (inputCheck == 1) {
            responseMessage = "Sorry, student name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, student name can only filled by letters";
        } else {
            if (majorService.majorExists(majorId)) {
                students.add(new Student(studentId, utility.inputTrim(name), majorId, true)); // Bila semua validasi nya terpenuhi, maka data akan ditambahkan
                result = getStudents().get((int) (studentId-1));
                responseMessage = "Data successfully added!";
            } else {
                responseMessage = "Sorry, id major doesn't already exists.";
            }
        }
        return result;
    }

    // Metode untuk mendapatkan semua daftar mahasiswa yang masih aktif
    public List<Student> getAllStudent() {
        List<Student> result = new ArrayList<>();
        for (Student student : getStudents()) {
            if (student.getStudentStatus()) { // Mengambil mahasiswa yang status nya aktif
                String majorName = getMajorName(student.getMajorId()); // Ambil nama jurusan sesuai id jurusan
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

    // Metode untuk memperbarui informasi mahasiswa
    public Student updateStudent(long studentId, String name, long majorId) {
        Student result = null;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, student name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, student name can only filled by letters";
        } else {
            if (studentExists(studentId) && getStudents().get((int) (studentId-1)).getStudentStatus()) {
                if (majorService.majorExists(majorId)) { // Bila semua validasi terpenuhi dan status nya aktif
                    getStudents().get((int) (studentId - 1)).setStudentName(utility.inputTrim(name));
                    getStudents().get((int) (studentId - 1)).setMajorId(majorId); // Maka nama mahasiwa, dan id jurusan akan diperbarui
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

    // Metode untuk menonaktifkan data mahasiswa berdasarkan id mahasiswa
    public boolean disableStudent(long studentId) {
        boolean result = false;
        if (studentExists(studentId) && getStudents().get((int) (studentId-1)).getStudentStatus()) { // Bila semua validasi terpenuhi dan status nya aktif
            getStudents().get((int) (studentId-1)).setStudentStatus(false); // Maka status mahasiswa akan diperbarui menjadi non aktif
            result = true;
            responseMessage = "Data deactivated successfully!";
        } else {
            responseMessage = "Sorry, id student is not found.";
        }
        return result;
    }

    // Metode untuk memvalidasi apakah mahasiswa tersebut sudah ada atau belum
    public boolean studentExists(long studentId) {
        boolean studentExists = false;
        int number = 0;
        while (!studentExists && number < getStudents().size()) {
            if (getStudents().get(number).getStudentStatus() && getStudents().get(number).getStudentId() == studentId) { // Jika mahasiswa yang dicari tersedia, maka akan memberikan boolean true
                studentExists = true;
            }
            number++;
        }
        return studentExists;
    }

    // Metode untuk mengambil nama berdasarkan id yang diberikan
    private String getMajorName(long majorId) {
        String nameMajor = "";
        for (Major major : majorService.getMajors()) {
            if (majorId == major.getMajorId()) {
                nameMajor = major.getNameMajor();
            }
        }
        return nameMajor;
    }
}
