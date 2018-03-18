package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.response.RegisterRequestJSON;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

/**
 * @author Praveen Singh
 */
@SpringBootTest(classes=UserService.class)
public class UserServiceTest{

    UserService userService;

    @Before
    public void setUp(){
        userService = new UserService();
    }

    @Test
    public void createUserShouldReturnAValidUserObject(){
        RegisterRequestJSON registerRequestJSON = new RegisterRequestJSON()
                .withUsername("praveen")
                .withPassword("singh")
                .withEmail("team207@gmail.com");

        User user = userService.createUserObject(registerRequestJSON);

        assertEquals(registerRequestJSON.getUsername(), user.getUsername());
        assertEquals(registerRequestJSON.getPassword(), user.getPassword());
        assertEquals(registerRequestJSON.getEmail(), user.getEmail());
        assertEquals(Integer.valueOf(1), user.getRole());
    }

}