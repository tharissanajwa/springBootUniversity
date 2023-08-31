package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.Major;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Kelas ini bertanggung jawab untuk mengelola data jurusan
@Service
public class MajorService {

    @Autowired
    private Utility utility;
    private static final List<Major> majors = new ArrayList<>(); // List untuk menyimpan data jurusan

    // Metode untuk mendapatkan daftar data jurusan
    public List<Major> getMajors () {
        return majors;
    }

    private String responseMessage; // Pesan status untuk memberi informasi kepada pengguna

    // Metode untuk mendapatkan pesan status
    public String getResponseMessage() {
        return responseMessage;
    }

    // Metode untuk menambahkan jurusan baru ke dalam daftar jurusan
    public Major addMajor (List<Major> majors, String name) {
        Major result = null;
        long majorId = majors.size() + 1; // Fungsinya untuk menambahkan id matkul secara otomatis
        int inputCheck = utility.inputCheck(utility.inputTrim(name)); // Fungsinya sebagai validasi dari nama yg diinputkan pengguna

        if (inputCheck == 1) {
            responseMessage = "Sorry, major name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, major name can only filled by letters";
        } else {
            majors.add(new Major(majorId, utility.inputTrim(name))); // Bila semua validasi nya terpenuhi, maka data akan ditambahkan
            result = getMajors().get((int) (majorId-1));
            responseMessage = "Data successfully added!";
        }
        return result;
    }

    // Metode untuk mendapatkan semua daftar jurusan yang masih aktif
    public List<Major> getAllMajor () {
        if (getMajors().size() == 0) {
            responseMessage = "Data not exists, please insert new data major.";
        } else {
            responseMessage = null;
        }
        return getMajors();
    }

    // Metode untuk memperbarui informasi jurusan
    public Major updateMajor(long majorId, String name) {
        Major result = null;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, major name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, major name can only filled by letters";
        } else {
            if (majorExists(majorId)) { // Bila validasi nya terpenuhi
                getAllMajor().get((int) (majorId-1)).setNameMajor(utility.inputTrim(name)); // Maka nama jurusan akan diperbarui
                result = getAllMajor().get((int) (majorId-1));
                responseMessage = "Data successfully updated!";
            } else {
                responseMessage = "Sorry, id major not found.";
            }
        }
        return result;
    }

    // Metode untuk memvalidasi apakah jurusan tersebut sudah ada atau belum
    public boolean majorExists(long majorId) {
        boolean majorExists = false;
        int number = 0;
        while (!majorExists && number < getMajors().size()) {
            if (getMajors().get(number).getMajorId() == majorId) { // Jika matkul yang dicari tersedia, maka akan memberikan boolean true
                majorExists = true;
            }
            number++;
        }
        return majorExists;
    }
}
