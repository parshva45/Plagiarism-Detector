package edu.northeastern.cs5500.response;

/**
 * @author Praveen Singh
 */
public class LoginResponseJSON {
    private Integer id;
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
}
