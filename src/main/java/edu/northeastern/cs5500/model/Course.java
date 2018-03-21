package edu.northeastern.cs5500.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Praveen Singh
 */
@Entity
@Table(name = "course")
public class Course implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String courseName;

    public Course withId(Integer id){
        this.setId(id);
        return this;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Course withCourseName(String courseName){
        this.setCourseName(courseName);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
