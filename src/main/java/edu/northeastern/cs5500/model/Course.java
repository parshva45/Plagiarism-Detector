package edu.northeastern.cs5500.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Praveen Singh
 */
public class Course implements Serializable{
    @Id
    @GeneratedValue
    private int courseId;
    private String name;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Course withCourseId(Integer courseId){
        this.courseId = courseId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course withCourseName(String name){
        this.name = name;
        return this;
    }
}
