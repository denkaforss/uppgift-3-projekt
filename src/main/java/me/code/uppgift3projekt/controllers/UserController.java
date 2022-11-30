package me.code.uppgift3projekt.controllers;

import lombok.AllArgsConstructor;
import me.code.uppgift3projekt.dto.UserDTO;
import me.code.uppgift3projekt.exception.UserAlreadyExistsException;
import me.code.uppgift3projekt.security.TokenService;
import me.code.uppgift3projekt.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final TokenService token;

    @PostMapping
    public ResponseEntity<UserDTO> register(@RequestBody Map<String, String> user) throws UserAlreadyExistsException {
        var newUser = service.register(user.get("username"), user.get("password"));
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user.get("username")));
    }

    @GetMapping
    public ResponseEntity<Collection<UserDTO>> getAll() {
        var allUsers = service.getAll();
        return ResponseEntity.ok(allUsers.stream().map(user -> new UserDTO(user.getUsername())).collect(Collectors.toList()));
    }

    @PostMapping("/login")
    public ResponseEntity<String> token(Authentication auth) {
        var generatedToken = token.generateToken(auth);
        System.out.println(generatedToken);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, generatedToken).body(generatedToken);
    }
}
