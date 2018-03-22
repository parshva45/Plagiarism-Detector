package edu.northeastern.cs5500.response;

import edu.northeastern.cs5500.model.User;

import java.util.List;

/**
 * @author Praveen Singh
 * A GetUser Response Object POJO Class.
 */
public class GetUserResponseJSON {
    private List<User> result;
    private String message;

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

    public GetUserResponseJSON withResult(List<User> result) {
        this.setResult(result);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GetUserResponseJSON withMessage(String message) {
        this.setMessage(message);
        return this;
    }
}
