package edu.northeastern.cs5500.service;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.repository.UserRepository;
import edu.northeastern.cs5500.response.RegisterRequestJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    /**
     * Method  to create a User Object from the given registerRequestJSON.
     * @param registerRequestJSON A instance of RegisterRequestJSON Pojo
     * @return an instance of RegisterRequestJson
     */
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

    /**
     * Gets a list of users based on the given userId or userName. If both provided
     * userId is taken on preference.
     * @param id Integer
     * @param userName String
     * @return a List of User Instances.
     */
    public List<User> findUserByUserIdOrUserName(String id, String userName){
        List<User> result = new ArrayList<>();
        if (id != null) {
            LOGGER.info("userSearch done using user id {}", id);
            result.add(userRepository.findById(Integer.parseInt(id)));
        } else if (userName != null) {
            LOGGER.info("userSearch done using user username {}", userName);
            result.addAll(userRepository.findByUserNameLike("%" + userName + "%"));
        }
        return result;
    }

    /**
     * This method saves the user instance in the databse and returns the same.
     * Also sends a email on successful save.
     * @param user Instance of User.
     * @return
     */
    public User addUserAndSendEmail(User user){
        LOGGER.info("saving user in database with username {}", user.getUserName());
        userRepository.save(user);
        LOGGER.info("sending email to user with username {}", user.getUserName());
        emailService.sendEmail(user.getUserName(), user.getEmail(), null);
        return user;
    }

    /**
     * Method returns if a user with the given username exists in the system.
     * @param userName String
     * @return boolean
     */
    public boolean checkIfUserExistsByUserName(String userName){
        LOGGER.info("checking if user exists with username {}", userName);
        return userRepository.existsByUserName(userName);
    }

    /**
     * Method to find users based on provided username and password.
     * @param userName String
     * @param password String
     * @return a List of User Instances.
     */
    public List<User> findUserByUserNameAndPassword(String userName, String password){
        LOGGER.info("get user with username {}", userName);
        return userRepository.findByUserNameAndPassword(userName, password);
    }

    /**
     * Method to update a user
     * @param user Instance of User
     * @return the updated instance of the User
     */
    public User updateUser(User user){
        return userRepository.save(user);
    }

}
