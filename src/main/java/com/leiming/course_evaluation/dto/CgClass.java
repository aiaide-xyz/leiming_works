package com.leiming.course_evaluation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//班级
@Entity


public class CgClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private String type;
    @OneToMany(mappedBy = "cgClass")
    private List<Student> students = new ArrayList<>();


    protected CgClass(){

    }

    @Override
    public String toString() {
        return "CgClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", type='" + type + '\'' +
                ", students=" + students +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public CgClass(String className, String type, List<Student> students) {
        this.className = className;
        this.type = type;
        this.students = students;
    }
}
