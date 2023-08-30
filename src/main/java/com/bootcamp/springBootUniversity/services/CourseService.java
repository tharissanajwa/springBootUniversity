package com.bootcamp.springBootUniversity.services;

import com.bootcamp.springBootUniversity.models.Course;
import com.bootcamp.springBootUniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private Utility utility;
    private List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public List<Course> addCourse(List<Course> courses, String name) {
        short courseId = (short) (courses.size() + 1);
        short cekInput = utility.cekInput(utility.inputTrim(name));

        if (cekInput == 1) {
            responseMessage = "Sorry, course name cannot be null.";
        } else if (cekInput == 2) {
            responseMessage = "Sorry, course name cannot be empty";
        } else if (cekInput == 3) {
            responseMessage = "Sorry, course name can only filled by letters";
        } else {
            courses.add(new Course(courseId, utility.inputTrim(name), true));
            responseMessage = "Data successfully added!";
        }
        return courses;
    }

    public boolean courseExists(short courseId) {
        boolean courseExists = false;
        for (Course course: getCourses()) {
            if (courseId == course.getCourseId()) {
                courseExists = true;
                break;
            }
        }
        return courseExists;
    }

    public List<Course> getAllCourse() {
        List<Course> result = new ArrayList<>();

        for (Course course : getCourses()) {
            if (course.getCourseStatus()) {
                result.add(course);
            }
        }

        if (result.isEmpty()) {
            responseMessage = "Data not exists, please insert new data course.";
        } else {
            responseMessage = null;
        }

        return result;
    }

    public List<Course> updateCourse(short courseId, String name) {
        List<Course> result = new ArrayList<>();
        short cekInput = utility.cekInput(utility.inputTrim(name));

        if (cekInput == 1) {
            responseMessage = "Sorry, course name cannot be null.";
        } else if (cekInput == 2) {
            responseMessage = "Sorry, course name cannot be empty";
        } else if (cekInput == 3) {
            responseMessage = "Sorry, course name can only filled by letters";
        } else {
            if (courseExists(courseId) && getCourses().get(courseId-1).getCourseStatus()) {
                getCourses().get(courseId-1).setCourseName(utility.inputTrim(name));
                Course course = getCourses().get(courseId-1);
                result.add(course);
                responseMessage = "Data successfully updated!";
            } else {
                responseMessage = "Sorry, id course is not found.";
            }
        }
        return result;
    }

    public List<Course> disableCourse(short courseId) {
        List<Course> result = new ArrayList<>();
        if (courseExists(courseId) && getCourses().get(courseId-1).getCourseStatus()) {
            getCourses().get(courseId-1).setCourseStatus(false);
            Course course = getCourses().get(courseId-1);
            result.add(course);
            responseMessage = "Data deactivated successfully!";
        } else {
            responseMessage = "Sorry, id course is not found.";
        }
        return result;
    }
}
