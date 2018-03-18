package edu.northeastern.cs5500.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Praveen Singh
 */

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Date createDate;
    private Integer role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User withId(Integer id) {
        this.setId(id);
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.setEmail(email);
        return this;
    }

//    public Date getCreateDate() {
//        return createDate;
//    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User withCreateDate(Date createDate) {
        this.setCreateDate(createDate);
        return this;
    }


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer level) {
        this.role = level;
    }

    public User withRole(Integer level) {
        this.setRole(level);
        return this;
    }

}
