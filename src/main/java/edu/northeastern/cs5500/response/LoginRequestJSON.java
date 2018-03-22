package edu.northeastern.cs5500.response;

/**
 * @author Praveen Singh
 * Simple LoginRequest POJO Class.
 */
public class LoginRequestJSON {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LoginRequestJSON withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequestJSON withPassword(String password) {
        this.setPassword(password);
        return this;
    }
}
