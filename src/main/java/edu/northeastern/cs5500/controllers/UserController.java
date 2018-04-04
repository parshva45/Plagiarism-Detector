package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.response.*;
import edu.northeastern.cs5500.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Praveen Singh
 * Rest Controller fot user services.
 */
@RestController
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Rest End point to log in a user
     * @param request LoginRequestJSON
     * @return ResponseEntity
     */
    @RequestMapping(path = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseJSON> login(@RequestBody LoginRequestJSON request) {
        LOGGER.info("Called login API for username {}", request.getUsername());
        List<User> result = userService.findUserByUserNameAndPassword(request.getUsername(), request.getPassword());

        if (result.isEmpty()) {
            LOGGER.info("login failed for username {}", request.getUsername());
            return ResponseEntity.badRequest().body(
                    new LoginResponseJSON()
                            .withMessage("credential not found")
            );
        } else {
            LOGGER.info("login successful for username {}", request.getUsername());
            return ResponseEntity.ok().body(
                    new LoginResponseJSON()
                            .withId(result.get(0).getId())
                            .withRole(result.get(0).getRole())
                            .withMessage("credential found")
            );
        }
    }


    /**
     * Rest endpoint for Register a user in system
     * @param request RegisterRequestJSON
     * @return RegisterResponseJSON instance
     */
    @RequestMapping(path = "/api/register", method = RequestMethod.POST)
    public ResponseEntity<RegisterResponseJSON> register(@RequestBody RegisterRequestJSON request) {
        LOGGER.info("called register API");
        if (userService.checkIfUserExistsByUserName(request.getUserName())) {
            LOGGER.info("register failed for username ={}, username already exists",
                    request.getUserName());
            return ResponseEntity.badRequest().body(
                    new RegisterResponseJSON()
                            .withMessage("username already exists")
            );
        }
        User user = userService.createUserObject(request);
        userService.addUserAndSendEmail(user);

        LOGGER.info("register successful for username ={}", request.getUserName());
        return ResponseEntity.ok().body(
                new RegisterResponseJSON()
                        .withId(user.getId())
                        .withRole(user.getRole())
                        .withMessage("user created")
        );
    }

    /**
     * Rest End point to Get user Details.
     * @param id Integer
     * @param userName String
     * @return ResponseEntity
     */
    @RequestMapping(path = "/api/user", method = RequestMethod.GET)
    public ResponseEntity<GetUserResponseJSON> getUser(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "username", required = false) String userName) {
        LOGGER.info("getUser API called with username ={}", userName);
        List<User> result = userService.findUserByUserIdOrUserName(id, userName);
        return ResponseEntity.ok().body(
                new GetUserResponseJSON()
                        .withResult(result)
                        .withMessage("results fetched")
        );
    }

    /**
     * Rest PUT endpoint to update a user.
     * @param request RegisterRequestJSON
     * @param userId Integer
     * @return ResponseEntity
     */
    @RequestMapping(path = "/api/user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<RegisterResponseJSON> updateUser(@RequestBody RegisterRequestJSON request,
                                                           @PathVariable("userId") String userId) {
        LOGGER.info("updateUser API called for username ={}", request.getUserName());
        if(!userService.findUserByUserIdOrUserName(userId, null).isEmpty()){
            User user = userService.createUserObject(request);
            userService.updateUser(user);
            return ResponseEntity.ok().body(
                    new RegisterResponseJSON()
                            .withId(user.getId())
                            .withRole(user.getRole())
                            .withMessage("user Updated")
            );
        }
        LOGGER.info("updateUser API failed to update for username ={}", request.getUserName());
        return ResponseEntity.badRequest().body(
                new RegisterResponseJSON()
                        .withMessage("user not found"));
    }


}
