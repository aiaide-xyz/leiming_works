package com.leiming.course_evaluation.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
//课程
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String mark;

    @ManyToMany(mappedBy = "courses")
    private Set<CgClass> cgClasses = new HashSet<>();
    protected Course(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Set<CgClass> getCgClasses() {
        return cgClasses;
    }

    public void setCgClasses(Set<CgClass> cgClasses) {
        this.cgClasses = cgClasses;
    }

    public Course(String courseName, String mark, Set<CgClass> cgClasses) {
        this.courseName = courseName;
        this.mark = mark;
        this.cgClasses = cgClasses;
    }
}
