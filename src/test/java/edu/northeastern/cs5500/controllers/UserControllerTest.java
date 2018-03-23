package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.response.*;
import edu.northeastern.cs5500.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest{

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    private LoginRequestJSON loginRequestJSON;

    @Before
    public void setUp(){
        loginRequestJSON = new LoginRequestJSON()
                .withUsername("praveen")
                .withPassword("singh");
    }

    @Test
    public void loginShouldPassWithValidInputs(){
        List<User> list = new ArrayList<>();
        list.add(new User().withUserName("praveen").withPassword("singh").withId(1).withRole(1));
        when(userService.findUserByUserNameAndPassword(loginRequestJSON.getUsername(),
                loginRequestJSON.getPassword()))
                .thenReturn(list);

        ResponseEntity responseEntity = userController.login(loginRequestJSON);
        LoginResponseJSON loginResponseJSON = (LoginResponseJSON) responseEntity.getBody();

        verify(userService, times(1)).findUserByUserNameAndPassword(loginRequestJSON.getUsername(),
                loginRequestJSON.getPassword());
        LoginResponseJSON loginResponseJSONExpected = new LoginResponseJSON()
                .withId(1)
                .withRole(1)
                .withMessage("credential found");

        assertNotNull(loginResponseJSON);
        assertEquals(loginResponseJSONExpected.getId(), loginResponseJSON.getId());
        assertEquals(loginResponseJSONExpected.getMessage(), loginResponseJSON.getMessage());
        assertEquals(loginResponseJSONExpected.getRole(), loginResponseJSON.getRole());
    }

    @Test
    public void loginShouldFailWithInvalidInputs(){
        when(userService.findUserByUserNameAndPassword(loginRequestJSON.getUsername(),
                loginRequestJSON.getPassword()))
                .thenReturn(new ArrayList<>());

        ResponseEntity responseEntity = userController.login(loginRequestJSON);
        LoginResponseJSON loginResponseJSON = (LoginResponseJSON) responseEntity.getBody();

        verify(userService, times(1)).findUserByUserNameAndPassword(loginRequestJSON.getUsername(),
                loginRequestJSON.getPassword());
        assertNotNull(loginResponseJSON);
        assertEquals("credential not found", loginResponseJSON.getMessage());
    }

    @Test
    public void registerShouldNotWorkWhenUserNameExists(){
        RegisterRequestJSON registerRequestJSON = new RegisterRequestJSON().withUserName("praveen");
        when(userService.checkIfUserExistsByUserName(loginRequestJSON.getUsername()))
                .thenReturn(true);

        ResponseEntity responseEntity = userController.register(registerRequestJSON);
        RegisterResponseJSON registerResponseJSON = (RegisterResponseJSON) responseEntity.getBody();

        verify(userService, times(1)).checkIfUserExistsByUserName("praveen");
        assertNotNull(registerResponseJSON);
        assertEquals("username already exists", registerResponseJSON.getMessage());
    }

    @Test
    public void registerShouldWorkWhenValidInputsGiven(){
        RegisterRequestJSON registerRequestJSON = new RegisterRequestJSON()
                .withUserName("praveen")
                .withPassword("singh")
                .withEmail("team207@gmail.com");
        User user = new User()
                .withUserName("praveen")
                .withPassword("singh")
                .withEmail("team207@gmail.com")
                .withRole(1)
                .withCreateDate(new Date())
                .withId(1);

        when(userService.createUserObject(registerRequestJSON)).thenReturn(user);
        when(userService.addUserAndSendEmail(user)).thenReturn(user);

        ResponseEntity responseEntity = userController.register(registerRequestJSON);
        RegisterResponseJSON registerResponseJSON = (RegisterResponseJSON) responseEntity.getBody();

        verify(userService, times(1)).createUserObject(registerRequestJSON);
        verify(userService, times(1)).addUserAndSendEmail(user);
        assertNotNull(registerResponseJSON);
        assertEquals("user created", registerResponseJSON.getMessage());
        assertEquals(Integer.valueOf(1), registerResponseJSON.getId());
        assertEquals(Integer.valueOf(1), registerResponseJSON.getRole());
    }


    @Test
    public void getUserShouldReturnUserWhenValidIdGiven(){
        String id = "1";
        User user = new User().withId(1).withUserName("praveen").withCreateDate(new Date());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.findUserByUserIdOrUserName(id, null)).thenReturn(userList);

        ResponseEntity responseEntity = userController.getUser(id, null);
        GetUserResponseJSON getUserResponseJSON = (GetUserResponseJSON) responseEntity.getBody();

        verify(userService, times(1)).findUserByUserIdOrUserName(id, null);
        assertEquals("results fetched",
                getUserResponseJSON.getMessage());
        assertFalse(getUserResponseJSON.getResult().isEmpty());
        assertNotNull(getUserResponseJSON.getResult().get(0));
        assertEquals(user, getUserResponseJSON.getResult().get(0));
        assertNotNull(getUserResponseJSON.getResult().get(0).getCreateDate());
    }

    @Test
    public void getUserShouldReturnUserWhenValidUserNameGiven(){
        String userName = "praveen";
        User user = new User().withId(1).withUserName("praveen");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.findUserByUserIdOrUserName(null, userName)).thenReturn(userList);

        ResponseEntity responseEntity = userController.getUser(null, userName);
        GetUserResponseJSON getUserResponseJSON = (GetUserResponseJSON) responseEntity.getBody();

        verify(userService, times(1)).findUserByUserIdOrUserName(null, userName );
        assertEquals("results fetched",
                getUserResponseJSON.getMessage());
        assertFalse(getUserResponseJSON.getResult().isEmpty());
        assertNotNull(getUserResponseJSON.getResult().get(0));
        assertEquals(user, getUserResponseJSON.getResult().get(0));
    }

    @Test
    public void getUserShouldReturnNotReturnAnyUserWhenIdOrUsernameNotGiven(){
        when(userService.findUserByUserIdOrUserName(null, null))
                .thenReturn(new ArrayList<>());

        ResponseEntity responseEntity = userController.getUser(null, null);
        GetUserResponseJSON getUserResponseJSON = (GetUserResponseJSON) responseEntity.getBody();

        assertEquals("results fetched",
                getUserResponseJSON.getMessage());
        assertTrue(getUserResponseJSON.getResult().isEmpty());
    }

    @Test
    public void updateUserShouldWork(){
        RegisterRequestJSON registerRequestJSON = new RegisterRequestJSON()
                .withUserName("praveen")
                .withPassword("singh")
                .withEmail("team207@gmail.com");
        User user = new User()
                .withUserName("praveen")
                .withPassword("singh")
                .withEmail("team207@gmail.com")
                .withRole(1)
                .withCreateDate(new Date())
                .withId(1);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.findUserByUserIdOrUserName(String.valueOf(user.getId()), null))
                .thenReturn(userList);
        when(userService.createUserObject(registerRequestJSON)).thenReturn(user);
        when(userService.updateUser(user)).thenReturn(user);

        ResponseEntity responseEntity = userController.updateUser(registerRequestJSON, "1");
        RegisterResponseJSON registerResponseJSON = (RegisterResponseJSON) responseEntity.getBody();

        verify(userService, times(1)).createUserObject(registerRequestJSON);
        verify(userService, times(1)).findUserByUserIdOrUserName(String.valueOf(user.getId()), null);
        verify(userService, times(1)).updateUser(user);
        assertNotNull(registerResponseJSON);
        assertEquals("user Updated", registerResponseJSON.getMessage());
        assertEquals(Integer.valueOf(1), registerResponseJSON.getId());
    }

    @Test
    public void updateShouldWorkAsExpected(){
        when(userService.updateUser(Mockito.any())).thenReturn(null);
        userController.updateUser(new RegisterRequestJSON(), "1");
    }

}