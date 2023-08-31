package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.Course;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Kelas ini bertanggung jawab untuk mengelola data matkul
@Service
public class CourseService {

    @Autowired
    private Utility utility;
    private static final List<Course> courses = new ArrayList<>(); // List untuk menyimpan data matkul

    // Metode untuk mendapatkan daftar data matkul
    public List<Course> getCourses() {
        return courses;
    }

    private String responseMessage; // Pesan status untuk memberi informasi kepada pengguna

    // Metode untuk mendapatkan pesan status
    public String getResponseMessage() {
        return responseMessage;
    }

    // Metode untuk menambahkan matkul baru ke dalam daftar matkul
    public Course addCourse(List<Course> courses, String name) {
        Course result = null;
        long courseId = courses.size() + 1; // Fungsinya untuk menambahkan id matkul secara otomatis
        int inputCheck = utility.inputCheck(utility.inputTrim(name)); // Fungsinya sebagai validasi dari nama yg diinputkan pengguna

        if (inputCheck == 1) {
            responseMessage = "Sorry, course name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, course name can only filled by letters";
        } else {
            courses.add(new Course(courseId, utility.inputTrim(name), true)); // Bila semua validasi nya terpenuhi, maka data akan ditambahkan
            result = getCourses().get((int) (courseId-1));
            responseMessage = "Data successfully added!";
        }
        return result;
    }

    // Metode untuk mendapatkan semua daftar matkul yang masih aktif
    public List<Course> getAllCourse() {
        List<Course> result = new ArrayList<>();
        for (Course course : getCourses()) {
            if (course.getCourseStatus()) { // Mengambil matkul yang status nya aktif
                result.add(course);
            }
        }
        if (result.isEmpty()) {
            responseMessage = "Data doesn't exists, please insert new data course.";
        } else {
            responseMessage = null;
        }

        return result;
    }

    // Metode untuk memperbarui informasi matkul
    public Course updateCourse(long courseId, String name) {
        Course result = null;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, course name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, course name can only filled by letters";
        } else {
            if (courseExists(courseId) && getCourses().get((int) (courseId-1)).getCourseStatus()) { // Bila semua validasi terpenuhi dan status nya aktif
                getCourses().get((int) (courseId-1)).setCourseName(utility.inputTrim(name)); // Maka nama matkul akan diperbarui
                result = getCourses().get((int) (courseId-1));
                responseMessage = "Data successfully updated!";
            } else {
                responseMessage = "Sorry, id course is not found.";
            }
        }
        return result;
    }

    // Metode untuk menonaktifkan data matkul berdasarkan id matkul
    public boolean disableCourse(long courseId) {
        boolean result = false;
        if (courseExists(courseId) && getCourses().get((int) (courseId-1)).getCourseStatus()) { // Bila semua validasi terpenuhi dan status nya aktif
            getCourses().get((int) (courseId-1)).setCourseStatus(false); // Maka status matkul akan diperbarui menjadi non aktif
            result = true;
            responseMessage = "Data deactivated successfully!";
        } else {
            responseMessage = "Sorry, id course is not found.";
        }
        return result;
    }

    // Metode untuk memvalidasi apakah matkul tersebut sudah ada atau belum
    public boolean courseExists(long courseId) {
        boolean courseExists = false;
        int number = 0;
        while (!courseExists && number < getCourses().size()) {
            if (getCourses().get(number).getCourseStatus() && getCourses().get(number).getCourseId() == courseId) { // Jika matkul yang dicari tersedia, maka akan memberikan boolean true
                courseExists = true;
            }
            number++;
        }
        return courseExists;
    }
}
