package com.example.lab06.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Course {
    private String courseID;
    private String courseName;
    private Timestamp releaseDate;
    private BigDecimal coursePrice;

    public Course() {

    }

    public Course(String courseID, String courseName, Timestamp releaseDate, BigDecimal coursePrice) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.releaseDate = releaseDate;
        this.coursePrice = coursePrice;
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

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Timestamp formatStringToTimestampDate(String releaseDateString) {
        Timestamp releaseDateT = null;
        if (releaseDateString != null && !releaseDateString.isEmpty()) {
            releaseDateT = Timestamp.valueOf(releaseDateString);
        }
        return  releaseDateT;
    }

    public Timestamp formatStringToTimestampDateTime(String releaseDateString) {
        Timestamp releaseDateT = null;
        if (releaseDateString != null && !releaseDateString.isEmpty()) {
            releaseDateString += " 00:00:00";
            releaseDateT = Timestamp.valueOf(releaseDateString);
        }
        return  releaseDateT;
    }
}
