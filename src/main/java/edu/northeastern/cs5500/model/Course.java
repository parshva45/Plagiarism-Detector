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
    private String name;

    public Course withId(Integer id){
        this.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course withCourseName(String name){
        this.setName(name);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
