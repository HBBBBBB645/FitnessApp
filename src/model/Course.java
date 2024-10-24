package model;

import java.util.ArrayList;
import java.util.Date;

public class Course {
    String courseID;
    String courseName;
    String description;
    ArrayList<Workout> workoutList;
    Coach coach;
    int duration;
    Date startDate;
    Date endDate;

    public Course(String courseID, String courseName, String description, ArrayList<Workout> workoutList, Coach coach, int duration, Date startDate, Date endDate) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.workoutList = workoutList;
        this.coach = coach;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public ArrayList<Workout> getWorkout() {
//        return workout;
//    }
//
//    public void setWorkout(Workout workout) {
//        this.workout = workout;
//    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}