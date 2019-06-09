package com.dentech.parentsapp.model;

import java.io.Serializable;

public class Marks implements Serializable {
    private String courseCode, courseUnit, grade, score;

    public Marks()  {
    }

    public Marks(String courseCode, String courseUnit, String grade, String score) {
        this.courseCode = courseCode;
        this.courseUnit = courseUnit;
        this.grade = grade;
        this.score = score;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
