package edu.northeastern.cs5500.model;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Praveen Singh
 */
@Entity
@Table(name = "studentHomework")
@IdClass(value = StudentHomeWork.StudentHomeWorkKey.class)
public class StudentHomeWork implements Serializable {

    @Id
    @Column(name = "userId")
    private int userId;
    @Id
    @Column(name = "homeWorkId")
    private int homeWorkId;
    @Id
    @Column(name="courseId")
    private int courseId;

    @Column(name = "filePath")
    private String filePath;

    @PersistenceConstructor
    private StudentHomeWork() {
        // empty on purpose
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHomeWorkId() {
        return homeWorkId;
    }

    public void setHomeWorkId(int homeWorkId) {
        this.homeWorkId = homeWorkId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public static class StudentHomeWorkKey implements Serializable {

        private int userId;
        private int homeWorkId;
        private int courseId;

        public StudentHomeWorkKey() {
            // empty on purpose
        }

        public StudentHomeWorkKey(final int userId, final int homeWorkId) {
            this();
            this.userId = userId;
            this.homeWorkId = homeWorkId;
        }
    }
}
