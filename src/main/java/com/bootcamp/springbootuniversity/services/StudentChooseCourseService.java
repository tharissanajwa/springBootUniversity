package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.StudentChooseCourse;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Kelas ini bertanggung jawab untuk mengelola data mahasiswa memilih matkul
@Service
public class StudentChooseCourseService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private Utility utility;

    private static final List<StudentChooseCourse> studentCourse = new ArrayList<>(); // List untuk menyimpan data mahasiswa memilih matkul

    // Metode untuk mendapatkan daftar data mahasiswa memilih matkul
    public List<StudentChooseCourse> getStudentCourse() {
        return studentCourse;
    }

    private String responseMessage; // Pesan status untuk memberi informasi kepada pengguna

    // Metode untuk mendapatkan pesan status
    public String getResponseMessage() {
        return responseMessage;
    }

    // Metode untuk menambahkan mahasiswa memilih matkul baru ke dalam daftar
    public StudentChooseCourse studentChooseCourses(List<StudentChooseCourse> studentCourse, long studentId, long courseId) {
        StudentChooseCourse result = null;
        long studentCourseId = studentCourse.size() + 1; // Fungsinya untuk menambahkan id mahasiswa memilih matkul secara otomatis
        if (studentService.studentExists(studentId)) {  // Fungsinya sebagai validasi untuk mengecek apakah mahasiswa dan matkul tersedia
            if (courseService.courseExists(courseId)) {
                studentCourse.add(new StudentChooseCourse(studentCourseId, studentId, courseId)); // Bila semua validasi nya terpenuhi, maka data akan ditambahkan
                result = getStudentCourse().get((int) (studentCourseId-1));
                responseMessage = "Students successfully selected course.";
            } else {
                responseMessage = "Sorry, id course doesn't already exists.";
            }
        } else {
            responseMessage = "Sorry, id student doesn't already exists.";
        }
        return result;
    }

    // Metode untuk mendapatkan semua daftar mahasiswa memilih matkul
    public List<StudentChooseCourse> getAllStudentCourse() {
        if (getStudentCourse().size() == 0) {
            responseMessage = "Data doesn't exists, please insert new data.";
        } else {
            responseMessage = null;
        }
        return getStudentCourse();
    }

    // Metode untuk memperbarui informasi id mahasiswa atau id matkul
    public StudentChooseCourse updateStudentCourse(long studentCourseId, long studentId, long courseId) {
        StudentChooseCourse studentChooseCourse = null;
        if (!studentCourseExists(studentCourseId)) { // Bila semua validasi terpenuhi
            responseMessage = "Sorry, id student course not found.";
        } else if (!studentService.studentExists(studentId)) {
            responseMessage = "Sorry, id student doesn't already exists.";
        } else if (!courseService.courseExists(courseId)) {
            responseMessage = "Sorry, id course doesn't already exists.";
        } else {
            getStudentCourse().get((int) (studentCourseId-1)).setStudentId(studentId); // Bila semua validasi terpenuhi maka id mahasiswa dan matkul akan diperbarui
            getStudentCourse().get((int) (studentCourseId-1)).setCourseId(courseId);
            studentChooseCourse = getStudentCourse().get((int) (studentCourseId-1));
            responseMessage = "Data successfully updated!";
        }
        return studentChooseCourse;
    }

    // Metode untuk menginputkan nilai mahasiswa
    public StudentChooseCourse inputStudentGrades(long studentCourseId, Integer quiz1, Integer quiz2, Integer quiz3, Integer exam1, Integer exam2) {
        StudentChooseCourse result = null;
        if (!studentCourseExists(studentCourseId)) {
            responseMessage = "Sorry, id student course not found.";
        } else if (utility.gradeCheck(quiz1) == 1 || utility.gradeCheck(quiz2) == 1 || utility.gradeCheck(quiz3) == 1 || utility.gradeCheck(exam1) == 1 || utility.gradeCheck(exam2) == 1) {
            responseMessage = "Sorry, the grades should be between 0-100";
        } else {
            getStudentCourse().get((int) (studentCourseId-1)).setQuiz1(quiz1); // Jika semua validasi terpenuhi, maka nilai akan diperbarui sesuai inputan pengguna
            getStudentCourse().get((int) (studentCourseId-1)).setQuiz2(quiz2);
            getStudentCourse().get((int) (studentCourseId-1)).setQuiz3(quiz3);
            getStudentCourse().get((int) (studentCourseId-1)).setExam1(exam1);
            getStudentCourse().get((int) (studentCourseId-1)).setExam2(exam2);
            result = getStudentCourse().get((int) (studentCourseId-1));
            responseMessage = "Grade entered successfully!";
        }
        return result;
    }

    // Metode untuk memvalidasi apakah mahasiswa memilih matkul tersebut sudah ada atau belum
    private boolean studentCourseExists(long studentCourseId) {
        boolean studentCourseIdExists = false;
        int number = 0;
        while (!studentCourseIdExists && number < getStudentCourse().size()) {
            if (getStudentCourse().get(number).getStudentCourseId() == studentCourseId) { // Jika mahasiswa memilih matkul yang dicari tersedia, maka akan memberikan boolean true
                studentCourseIdExists = true;
            }
        }
        return studentCourseIdExists;
    }
}
