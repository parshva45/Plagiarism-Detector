package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.repository.UserRepository;
import edu.northeastern.cs5500.response.RegisterRequestJSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Praveen Singh
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest{

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

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

    @Test
    public void findUserByUserNameOrIdShouldReturnEmptyListIfBothPassedAsNull(){
        List<User> result = userService.findUserByUserIdOrUserName(null, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void findUserByUserNameOrIdShouldReturnUserWhenIdIsPassed(){
        User user = new User().withId(1).withUsername("praveen");
        when(userRepository.findById(1)).thenReturn(user);

        List<User> result = userService.findUserByUserIdOrUserName("1", null);

        verify(userRepository, times(1)).findById(1);
        assertFalse(result.isEmpty());
        assertEquals(user, result.get(0));
    }

    @Test
    public void findUserByUserNameOrIdShouldReturnUserWhenUserNameIsPassed(){
        String userName = "praveen";
        User user = new User().withId(1).withUsername("praveen");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findByUsernameLike("%" + userName + "%")).thenReturn(userList);

        List<User> result = userService.findUserByUserIdOrUserName(null, userName);

        verify(userRepository, times(1)).findByUsernameLike("%" + userName + "%");
        assertFalse(result.isEmpty());
        assertEquals(user, result.get(0));
    }

}