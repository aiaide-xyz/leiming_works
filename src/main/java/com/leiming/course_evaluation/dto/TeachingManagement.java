package com.leiming.course_evaluation.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public TeachingManagement(String batch, String cgClass, String course, String teacher, String department) {
        this.batch = batch;
        this.cgClass = cgClass;
        this.course = course;
        this.teacher = teacher;
        this.department = department;
    }
}
