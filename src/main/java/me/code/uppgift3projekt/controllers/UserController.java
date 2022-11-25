package me.code.uppgift3projekt.controllers;

import lombok.AllArgsConstructor;
import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.exception.UserAlreadyExistsException;
import me.code.uppgift3projekt.security.TokenService;
import me.code.uppgift3projekt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final TokenService token;

    @PostMapping
    public ResponseEntity<User> register(@RequestBody Map<String, String> user) throws UserAlreadyExistsException {
        var newUser = service.register(user.get("username"), user.get("password"));
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getAll() {
        var allUsers = service.getAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/login")
    public ResponseEntity<String> token(Authentication auth) {
        var generatedToken = token.generateToken(auth);
        System.out.println(generatedToken);
        return ResponseEntity.ok().header("Authorization", generatedToken).body("");
    }
}
