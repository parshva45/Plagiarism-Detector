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
    private final RegisterEmail registerEmail;

    @Autowired
    public UserService(UserRepository userRepository, RegisterEmail registerEmail) {
        this.userRepository = userRepository;
        this.registerEmail = registerEmail;
    }

    public User createUserObject(RegisterRequestJSON registerRequestJSON){
       return new User()
               .withUserName(registerRequestJSON.getUserName())
               .withPassword(registerRequestJSON.getPassword())
               .withEmail(registerRequestJSON.getEmail())
               .withLastName(registerRequestJSON.getLastName())
               .withFirstName(registerRequestJSON.getFirstName())
               .withCreateDate(new Date())
               .withRole(1);
    }

    public List<User> findUserByUserIdOrUserName(String id, String userName){
        List<User> result = new ArrayList<>();
        if (id != null) {
            result.add(userRepository.findById(Integer.parseInt(id)));
        } else if (userName != null) {
            result.addAll(userRepository.findByUserNameLike("%" + userName + "%"));
        }
        return result;
    }

    public User addUserAndSendEmail(User user){
        userRepository.save(user);
        registerEmail.sendEmail(user.getUserName(), user.getEmail());
        return user;
    }

    public boolean checkIfUserExistsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public List<User> findUserByUserNameAndPassword(String userName, String password){
        return userRepository.findByUserNameAndPassword(userName, password);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

}
