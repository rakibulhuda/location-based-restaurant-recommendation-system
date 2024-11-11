package sqa.project.back_end.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sqa.project.back_end.models.User;
import sqa.project.back_end.service.UserService;

@RestController
@RequestMapping("/user/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        try {
            boolean isAuthenticated = userService.authenticateUser(user.getUserId(), user.getPassword());
            if (isAuthenticated) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
    }
}
