package me.code.uppgift3projekt.controllers;

import lombok.AllArgsConstructor;
import me.code.uppgift3projekt.data.Post;
import me.code.uppgift3projekt.exception.PostAlreadyExistsException;
import me.code.uppgift3projekt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class PostController {

    private final PostService service;

    @PostMapping("/post")
    public ResponseEntity<Post> create(@RequestBody Post post) throws PostAlreadyExistsException {
        var newPost = service.create(post.getCreator(), post.getTitle(), post.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }


}
