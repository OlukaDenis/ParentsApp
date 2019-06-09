package com.dentech.parentsapp.model;

public class Student {
    private String student_number, name, course, password;

    public Student() {
    }

    public Student(String name, String course, String password) {
        this.name = name;
        this.course = course;
        this.password = password;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
