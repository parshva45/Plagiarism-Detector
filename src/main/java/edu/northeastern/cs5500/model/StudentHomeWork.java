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

    @Column(name = "filePath")
    private String filePath;

    @PersistenceConstructor
    private StudentHomeWork() {
        // empty on purpose
    }

    public StudentHomeWork(final int userId, final int homeWorkId, final String filePath) {
        this();

        this.userId = userId;
        this.homeWorkId = homeWorkId;
        this.filePath = filePath;
    }


    public static class StudentHomeWorkKey implements Serializable {

        private int userId;
        private int homeWorkId;

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
