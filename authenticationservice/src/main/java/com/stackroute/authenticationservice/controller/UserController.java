package com.stackroute.authenticationservice.controller;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserNotFoundException;
import com.stackroute.authenticationservice.service.SecurityTokenGenerator;
import com.stackroute.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/userservice")
public class UserController {

    private ResponseEntity responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


//    @PostMapping("/save")
//    public ResponseEntity saveuser(@RequestBody User user) {
//        userService.saveUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) throws UserNotFoundException {
        Map<String, String> map = null;
        try {
            User userObj = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (userObj.getUsername().equals(user.getUsername())) {
                map = securityTokenGenerator.generateToken(user);
            }
            return ResponseEntity.status(HttpStatus.OK).body(map);
        } catch (UserNotFoundException ex) {
            throw new UserNotFoundException();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Try Again..");
        }
    }
}
