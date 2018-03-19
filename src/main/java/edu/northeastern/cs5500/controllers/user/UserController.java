package edu.northeastern.cs5500.controllers.user;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.repository.UserRepository;
import edu.northeastern.cs5500.response.*;
import edu.northeastern.cs5500.service.RegisterEmail;
import edu.northeastern.cs5500.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Praveen Singh
 */
@RestController
public class UserController {
    private final UserRepository userRepository;
    private final RegisterEmail registerEmail;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,
                          RegisterEmail registerEmail, UserService userService) {
        this.userRepository = userRepository;
        this.registerEmail = registerEmail;
        this.userService = userService;
    }

    @RequestMapping(path = "/api/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponseJSON> login(@RequestBody LoginRequestJSON request) {
        List<User> result = userRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword()
        );
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body(
                    new LoginResponseJSON()
                            .withMessage("credential not found")
            );
        } else {
            return ResponseEntity.ok().body(
                    new LoginResponseJSON()
                            .withId(result.get(0).getId())
                            .withMessage("credential found")
            );
        }
    }

    @RequestMapping(path = "/api/register", method = RequestMethod.POST)
    public ResponseEntity<RegisterResponseJSON> register(@RequestBody RegisterRequestJSON request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(
                    new RegisterResponseJSON()
                            .withMessage("username already exists")
            );
        }
        User user = userService.createUserObject(request);
        userRepository.save(user);
        registerEmail.sendEmail(user.getUsername(), user.getEmail());
        return ResponseEntity.ok().body(
                new RegisterResponseJSON()
                        .withId(user.getId())
                        .withMessage("user created")
        );
    }

    @RequestMapping(path = "/api/user", method = RequestMethod.GET)
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
}
