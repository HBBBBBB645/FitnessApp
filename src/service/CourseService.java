package service;

import model.Course;
import model.JDBC;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseService {
   private List<Course> courses = new ArrayList<>(); // Danh sách các khóa học

    public void addCourse() {
        String courseName = Utils.getString("Enter Course Name", Utils.input);
        String description = Utils.getString("Enter Course Description", Utils.input);
        
        LocalTime duration = LocalTime.parse(Utils.getString("Enter Course Duration (HH:mm)", Utils.input));

        Course newCourse = new Course(courseID, courseName, description, null, null, duration);
        courses.add(newCourse);
        System.out.println("Course added successfully!");
    }

    public void showCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("List of Courses:");
            for (Course course : courses) {
                System.out.println(course.toString()); 
            }
        }
    }

    public Course findCourseByID(String courseID) {
        for (Course course : courses) {
            if (course.getCourseID().equals(courseID)) {
                return course;
            }
        }
        return null; 
    }

    public void deleteCourse(String courseID) {
        Course course = findCourseByID(courseID);
        if (course != null) {
            courses.remove(course);
            System.out.println("Course removed successfully!");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void updateCourse(String courseID) {
        Course course = findCourseByID(courseID);
        if (course != null) {
            String fieldName = Utils.getString("Enter field name to update (courseName, description, duration)", Utils.input);
            String newValue = Utils.getString("Enter new value", Utils.input);
            try {
                Field field = Course.class.getDeclaredField(fieldName);
                field.setAccessible(true); 
                if (field.getType() == String.class) {
                    field.set(course, newValue); 
                } else if (field.getType() == LocalTime.class) {
                    LocalTime newDuration = LocalTime.parse(newValue); 
                    field.set(course, newDuration);
                }
                System.out.println("Course updated successfully!");
            } catch (NoSuchFieldException e) {
                System.out.println("Field not found.");
            } catch (IllegalAccessException e) {
                System.out.println("Cannot access field.");
            } catch (Exception e) {
                System.out.println("Error updating field: " + e.getMessage());
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    private static String autoGenerateCourseID(Connection connection) {
        String newCourseId = "CS001";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(CourseID) AS maxID FROM tblCourse");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String maxID = resultSet.getString("maxID");
                if (maxID != null) {
                    int idNumber = Integer.parseInt(maxID.substring(1)) + 1;
                    newCourseId = String.format("CS%03d", idNumber);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
        return newCourseId;
    }
}
