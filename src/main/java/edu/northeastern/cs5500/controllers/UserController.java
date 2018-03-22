package edu.northeastern.cs5500.controllers;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.response.*;
import edu.northeastern.cs5500.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Praveen Singh
 * Rest Controller fot user services.
 */
@RestController
@Api(value="User Controller", description="Operations related to user services")
public class UserController {
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
    @ApiOperation(value = "Log in a user in the system.")
    public ResponseEntity<LoginResponseJSON> login(@RequestBody LoginRequestJSON request) {
        List<User> result = userService.findUserByUserNameAndPassword(request.getUsername(), request.getPassword());

        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new LoginResponseJSON()
                            .withMessage("credential not found")
            );
        } else {
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
    @ApiOperation(value = "Register a user in the system.")
    public ResponseEntity<RegisterResponseJSON> register(@RequestBody RegisterRequestJSON request) {
        if (userService.checkIfUserExistsByUserName(request.getUserName())) {
            return ResponseEntity.badRequest().body(
                    new RegisterResponseJSON()
                            .withMessage("username already exists")
            );
        }
        User user = userService.createUserObject(request);
        userService.addUserAndSendEmail(user);

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
    @ApiOperation(value = "Get user details of a user based on the UserId..")
    public ResponseEntity<GetUserResponseJSON> getUser(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "username", required = false) String userName) {
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
    @ApiOperation(value = "update a user for the give userID")
    public ResponseEntity<RegisterResponseJSON> updateUser(@RequestBody RegisterRequestJSON request,
                                                           @PathVariable("userId") String userId) {
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
        return ResponseEntity.badRequest().body(
                new RegisterResponseJSON()
                        .withMessage("user not found"));
    }


}
