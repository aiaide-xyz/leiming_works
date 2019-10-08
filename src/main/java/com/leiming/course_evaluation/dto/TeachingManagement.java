package com.leiming.course_evaluation.dto;

import javax.persistence.*;

//授课管理
@Entity
public class TeachingManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String batch;
    private String cgClass;
    private String course;
    private String teacher;
    private String department;
    @Transient
    private String status = "未评教";
    @Transient
    private String status2;
    @Transient
    private String finalScore = "未结算";
    @Transient
    private String teacherNumber;
    protected TeachingManagement(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getCgClass() {
        return cgClass;
    }

    public void setCgClass(String cgClass) {
        this.cgClass = cgClass;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public TeachingManagement(String batch, String cgClass, String course, String teacher, String department, String status, String status2, String finalScore, String teacherNumber) {
        this.batch = batch;
        this.cgClass = cgClass;
        this.course = course;
        this.teacher = teacher;
        this.department = department;
        this.status = status;
        this.status2 = status2;
        this.finalScore = finalScore;
        this.teacherNumber = teacherNumber;
    }
}
