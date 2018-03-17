package edu.northeastern.cs5500.response;

/**
 * @author Praveen Singh
 */
public class RegisterRequestJSON {
    private String username;
    private String password;
    private String emailId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RegisterRequestJSON withUsername(String username) {
        this.setUsername(username);
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RegisterRequestJSON withPassword(String password) {
        this.setPassword(password);
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String email) {
        this.emailId = email;
    }

    public RegisterRequestJSON withEmail(String email) {
        this.setEmailId(email);
        return this;
    }
}
