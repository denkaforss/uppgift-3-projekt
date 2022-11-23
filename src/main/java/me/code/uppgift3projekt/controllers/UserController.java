package me.code.uppgift3projekt.controllers;

import lombok.AllArgsConstructor;
import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.exception.UserAlreadyExistsException;
import me.code.uppgift3projekt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<User> register(@RequestBody Map<String, String> user) throws UserAlreadyExistsException {
        var newUser = service.register(user.get("username"), user.get("password"));
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
