package com.leiming.course_evaluation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//院系实体
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dptName;
    private String mark;
    private String status = "未开启";
    @OneToMany(mappedBy = "department")//与学生建立一对多关系
    private List<Student> students;
    @OneToMany(mappedBy = "department")//与教师建立一对多关系
    private List<Teacher> teachers = new ArrayList<>();
    @OneToMany(mappedBy = "department")
    private List<CgClass> cgClasses = new ArrayList<>();
    protected Department(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<CgClass> getCgClasses() {
        return cgClasses;
    }

    public void setCgClasses(List<CgClass> cgClasses) {
        this.cgClasses = cgClasses;
    }

    public Department(String dptName, String mark, String status, List<Student> students, List<Teacher> teachers, List<CgClass> cgClasses) {
        this.dptName = dptName;
        this.mark = mark;
        this.status = status;
        this.students = students;
        this.teachers = teachers;
        this.cgClasses = cgClasses;
    }
}
