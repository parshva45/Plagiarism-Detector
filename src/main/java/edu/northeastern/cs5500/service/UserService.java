package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.repository.UserRepository;
import edu.northeastern.cs5500.response.RegisterRequestJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Praveen Singh
 */
@Component
public class UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUserObject(RegisterRequestJSON registerRequestJSON){
       return new User()
               .withUsername(registerRequestJSON.getUsername())
               .withPassword(registerRequestJSON.getPassword())
               .withEmail(registerRequestJSON.getEmail())
               .withCreateDate(new Date())
               .withRole(1);
    }

    public List<User> findUserByUserIdOrUserName(String id, String userName){
        List<User> result = new ArrayList<>();
        if (id != null) {
            result.add(userRepository.findById(Integer.parseInt(id)));
        } else if (userName != null) {
            result.addAll(userRepository.findByUsernameLike("%" + userName + "%"));
        }
        return result;
    }

}
