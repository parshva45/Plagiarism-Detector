package edu.northeastern.cs5500.model;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Praveen Singh
 */
@Entity
@Table(name = "studentHomework")
public class StudentHomeWork implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "userId", nullable = false)
    private int userId;

    @Column(name = "homeWorkId", nullable = false)
    private int homeWorkId;

    @Column(name="courseId", nullable = false)
    private int courseId;

    @Column(name = "filePath")
    private String filePath;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public StudentHomeWork withUserId(Integer userId){
        this.setUserId(userId);
        return this;
    }

    public StudentHomeWork withId(Integer id){
        this.setId(id);
        return this;
    }

    public int getHomeWorkId() {
        return homeWorkId;
    }

    public void setHomeWorkId(int homeWorkId) {
        this.homeWorkId = homeWorkId;
    }

    public StudentHomeWork withHomeWorkId(Integer homeWorkId){
        this.setHomeWorkId(homeWorkId);
        return this;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public StudentHomeWork withCourseId(Integer courseId){
        this.setCourseId(courseId);
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public StudentHomeWork withPath(String path){
        this.setFilePath(path);
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
