package repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.Workout;
import model.Coach;

public class CourseRepo {
    private static final String FILE_PATH = "course.csv";

    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String line;
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            // Bỏ qua dòng tiêu đề
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    String courseID = fields[0].trim();
                    String courseName = fields[1].trim();
                    String description = fields[2].trim();
                    Workout workout = new Workout(fields[3].trim());  
                    Coach coach = new Coach(fields[4].trim());  

                    LocalTime duration = null;
                    try {
                        duration = LocalTime.parse(fields[5].trim());
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid time format for course " + courseID + ": " + fields[5]);
                    }

                    Course course = new Course(courseID, courseName, description, workout, coach, duration);
                    courses.add(course);
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return courses;
    }
    public void writeAllCourse(List<Course> courses) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("CourseID,CourseName,Description,Workout,Coach,Duration");
            bw.newLine();

            for (Course course : courses) {
                StringBuilder sb = new StringBuilder();
                sb.append(course.getCourseID()).append(",");
                sb.append(course.getCourseName()).append(",");
                sb.append(course.getDescription()).append(",");
                sb.append(course.getWorkout().toString()).append(",");  
                sb.append(course.getCoach().toString()).append(",");
                sb.append(course.getDuration().toString()); 
                
                bw.write(sb.toString());
                bw.newLine(); 
            }
            
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
