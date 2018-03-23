package edu.northeastern.cs5500.response;

/**
 * @author Praveen Singh
 * Simple LoginResponse POJO class.
 */
public class LoginResponseJSON {
    private Integer id;
    private Integer role;
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LoginResponseJSON withId(Integer id) {
        this.setId(id);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginResponseJSON withMessage(String message) {
        this.setMessage(message);
        return this;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public LoginResponseJSON withRole(Integer role){
        this.setRole(role);
        return this;
    }
}
