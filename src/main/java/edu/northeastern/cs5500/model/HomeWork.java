package edu.northeastern.cs5500.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Praveen Singh
 * Entity for HomeWork Table.
 * The fields are columns of the table.
 */
@Entity
@Table(name = "homework")
public class HomeWork implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private int courseId;
    private int homeWorkNumber;
    private String description;
    private int professorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HomeWork withId(Integer id){
        this.setId(id);
        return this;
    }

    public int getHomeWorkNumber() {
        return homeWorkNumber;
    }

    public void setHomeWorkNumber(int homeWorkNumber) {
        this.homeWorkNumber = homeWorkNumber;
    }

    public HomeWork withHomeWorkNumber(Integer homeWorkNumber){
        this.setHomeWorkNumber(homeWorkNumber);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HomeWork withDescription(String description){
        this.setDescription(description);
        return this;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public HomeWork withProfessorId(Integer professorId){
        this.setProfessorId(professorId);
        return this;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public HomeWork withCourseId(Integer courseId){
        this.setCourseId(courseId);
        return this;
    }
}
