package edu.northeastern.cs5500.response;

/**
 * @author Praveen Singh
 * Simple RegisterRequest POJO class.
 */
public class RegisterRequestJSON {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public RegisterRequestJSON withUserName(String username) {
        this.setUserName(username);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterRequestJSON withEmail(String email) {
        this.setEmail(email);
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public RegisterRequestJSON withFirstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public RegisterRequestJSON withLastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }
}
