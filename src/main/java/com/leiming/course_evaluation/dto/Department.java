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
    @OneToMany(mappedBy = "department")//与院系建立一对多关系
    private List<Student> students;
    @OneToMany(mappedBy = "department")//与教师建立一对多关系
    private List<Teacher> teachers = new ArrayList<>();
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

    public Department(String dptName, String mark, List<Student> students, List<Teacher> teachers) {
        this.dptName = dptName;
        this.mark = mark;
        this.students = students;
        this.teachers = teachers;
    }
}
