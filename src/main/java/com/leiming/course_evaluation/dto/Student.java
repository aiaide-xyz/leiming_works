package com.leiming.course_evaluation.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
//学生实体
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String sex;
    private String stuNumber;
    @Transient
    private String className;
    @Transient
    private String dptName;
    @ManyToOne
    @JoinColumn(name = "cg_class_id")
    @JsonBackReference
    private CgClass cgClass;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;



    protected Student(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    public CgClass getCgClass() {
        return cgClass;
    }

    public void setCgClass(CgClass cgClass) {
        this.cgClass = cgClass;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Student(String username, String password, String sex, String stuNumber, String className, String dptName, CgClass cgClass, Department department) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.stuNumber = stuNumber;
        this.className = className;
        this.dptName = dptName;
        this.cgClass = cgClass;
        this.department = department;
    }
}
