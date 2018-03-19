package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.response.RegisterRequestJSON;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Praveen Singh
 */
@Component
public class UserService{

    public User createUserObject(RegisterRequestJSON registerRequestJSON){
       return new User()
               .withUsername(registerRequestJSON.getUsername())
               .withPassword(registerRequestJSON.getPassword())
               .withEmail(registerRequestJSON.getEmail())
               .withCreateDate(new Date())
               .withRole(1);
    }
}
