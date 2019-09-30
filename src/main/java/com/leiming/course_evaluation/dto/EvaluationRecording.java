package com.leiming.course_evaluation.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author 雷鸣
 * @ClassName evaluationRecording
 * LovelyLM
 * @Date 2019/9/29 10:31
 */
@Entity

public class EvaluationRecording {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //老师or学生
    private String userType;

    private String teacher;
    private String course;

    private String teacherNumber;

    private String batch;

    private String username;
    private String userNumber;
    private String className;
    private String department;
    //选择的选项：概念的讲解-非常好,迟到早退问题-经常迟到早退
    private String selectedContent;
    private String score;
    public EvaluationRecording(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSelectedContent() {
        return selectedContent;
    }

    public void setSelectedContent(String selectedContent) {
        this.selectedContent = selectedContent;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public EvaluationRecording(String userType, String teacher, String course, String teacherNumber, String batch, String username, String userNumber, String className, String department, String selectedContent, String score) {
        this.userType = userType;
        this.teacher = teacher;
        this.course = course;
        this.teacherNumber = teacherNumber;
        this.batch = batch;
        this.username = username;
        this.userNumber = userNumber;
        this.className = className;
        this.department = department;
        this.selectedContent = selectedContent;
        this.score = score;
    }
}
