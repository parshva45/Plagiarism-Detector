package edu.northeastern.cs5500.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Praveen Singh
 */

@Entity
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User withUserName(String userName) {
        this.setUserName(userName);
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

    private void setCreateDate(Date createDate) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public User withFirstName(String firstName){
        this.setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public User withLastName(String lastName){
        this.setLastName(lastName);
        return this;
    }

    public Date getCreateDate() {
        return createDate;
    }
}
