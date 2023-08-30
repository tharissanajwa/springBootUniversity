package com.bootcamp.springbootuniversity.services;

import com.bootcamp.springbootuniversity.models.Course;
import com.bootcamp.springbootuniversity.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private Utility utility;
    private static final List<Course> courses = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public Course addCourse(List<Course> courses, String name) {
        Course result = null;
        long courseId = courses.size() + 1;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, course name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, course name can only filled by letters";
        } else {
            courses.add(new Course(courseId, utility.inputTrim(name), true));
            result = getCourses().get((int) (courseId-1));
            responseMessage = "Data successfully added!";
        }
        return result;
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

    public Course updateCourse(long courseId, String name) {
        Course result = null;
        int inputCheck = utility.inputCheck(utility.inputTrim(name));

        if (inputCheck == 1) {
            responseMessage = "Sorry, course name cannot be blank.";
        } else if (inputCheck == 2) {
            responseMessage = "Sorry, course name can only filled by letters";
        } else {
            if (courseExists(courseId) && getCourses().get((int) (courseId-1)).getCourseStatus()) {
                getCourses().get((int) (courseId-1)).setCourseName(utility.inputTrim(name));
                result = getCourses().get((int) (courseId-1));
                responseMessage = "Data successfully updated!";
            } else {
                responseMessage = "Sorry, id course is not found.";
            }
        }
        return result;
    }

    public boolean disableCourse(long courseId) {
        boolean result = false;
        if (courseExists(courseId) && getCourses().get((int) (courseId-1)).getCourseStatus()) {
            getCourses().get((int) (courseId-1)).setCourseStatus(false);
            result = true;
            responseMessage = "Data deactivated successfully!";
        } else {
            responseMessage = "Sorry, id course is not found.";
        }
        return result;
    }

    public boolean courseExists(long courseId) {
        boolean courseExists = false;
        int number = 0;
        while (!courseExists && number < getCourses().size()) {
            if (getCourses().get(number).getCourseStatus() && getCourses().get(number).getCourseId() == courseId) {
                courseExists = true;
            }
            number++;
        }
        return courseExists;
    }
}
