package edu.northeastern.cs5500.controllers.user;

import edu.northeastern.cs5500.model.User;
import edu.northeastern.cs5500.repository.UserRepository;
import edu.northeastern.cs5500.response.*;
import edu.northeastern.cs5500.service.RegisterEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Praveen Singh
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

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
        Date now = new Date();
        User user = new User()
                .withUsername(request.getUsername())
                .withPassword(request.getPassword())
                .withEmail(request.getEmailId())
                .withCreateDate(now)
                .withRole(1);
        userRepository.save(user);
//        RegisterEmail.sendEmail(user.getUsername(), user.getEmail());
        return ResponseEntity.ok().body(
                new RegisterResponseJSON()
//                        .withId(User.getId())
                        .withMessage("user created")
        );
    }

    @RequestMapping(path = "/api/user", method = RequestMethod.GET)
    public ResponseEntity<GetUserResponseJSON> getCustomer(
            @RequestParam(name = "id", required = false) String id,
            @RequestParam(name = "username", required = false) String username) {
        List<User> result = new ArrayList<>();
        if (id != null) {
            result.add(userRepository.findById(Integer.parseInt(id)));
        } else if (username != null) {
            result.addAll(userRepository.findByUsernameLike("%" + username + "%"));
        }
        return ResponseEntity.ok().body(
                new GetUserResponseJSON()
                        .withResult(result)
                        .withMessage("results fetched")
        );
    }
}
