package edu.northeastern.cs5500.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Praveen Singh
 */
@Entity
@Table(name = "systemstatus")
public class SystemStatus {
    @Id
    @GeneratedValue
    private int id;

    private int professor;
    private double score;
    private int user1;
    private int user2;
    private int homeworkId;
    private int courseId;
    private String strategy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfessor() {
        return professor;
    }

    public void setProfessor(int professor) {
        this.professor = professor;
    }

    public SystemStatus withProfessorId(Integer id) {
        this.setProfessor(id);
        return this;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public SystemStatus withScore(double score) {
        this.setScore(score);
        return this;
    }

    public int getUser1() {
        return user1;
    }

    public void setUser1(int user1) {
        this.user1 = user1;
    }

    public SystemStatus withUser1(int user1) {
        this.setUser1(user1);
        return this;
    }

    public int getUser2() {
        return user2;
    }

    public void setUser2(int user2) {
        this.user2 = user2;
    }

    public SystemStatus withUser2(int user2) {
        this.setUser2(user2);
        return this;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public SystemStatus withHomeWorkId(int homeWorkId) {
        this.setHomeworkId(homeWorkId);
        return this;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public SystemStatus withCourse(int courseId) {
        this.setCourseId(courseId);
        return this;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public SystemStatus withStrategy(String strategy) {
        this.setStrategy(strategy);
        return this;
    }
}
