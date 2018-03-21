package edu.northeastern.cs5500.response;

/**
 * @author Praveen Singh
 */
public class UploadFileJSON {
    private String path;
    private String message;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public UploadFileJSON withPath(String path){
        this.setPath(path);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UploadFileJSON withMessage(String message){
        this.setMessage(message);
        return this;
    }
}
