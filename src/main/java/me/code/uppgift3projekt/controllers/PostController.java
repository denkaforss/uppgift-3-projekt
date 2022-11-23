package me.code.uppgift3projekt.controllers;

import lombok.AllArgsConstructor;
import me.code.uppgift3projekt.data.Post;
import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.exception.NotOwnerException;
import me.code.uppgift3projekt.exception.PostAlreadyExistsException;
import me.code.uppgift3projekt.exception.PostDoesNotExistException;
import me.code.uppgift3projekt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@AllArgsConstructor
@RestController
public class PostController {

    private final PostService service;

    @PostMapping("/post")
    public ResponseEntity<Post> create(@RequestBody Post post) throws PostAlreadyExistsException {
        var newPost = service.create(post.getCreator(), post.getTitle(), post.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @DeleteMapping("/post/{title}")
    public ResponseEntity<Post> delete(@RequestBody User user, @PathVariable String title) throws NotOwnerException, PostDoesNotExistException {
        var deletedPost = service.delete(user, title);
        return ResponseEntity.status(HttpStatus.OK).body(deletedPost);
    }

    @PutMapping("/post/{title}")
    public ResponseEntity<Post> edit(@RequestBody Post post, @PathVariable String title) throws NotOwnerException, PostDoesNotExistException {
        var editedPost = service.edit(post.getCreator(), title, post.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(editedPost);
    }

    @GetMapping("/post")
    public ResponseEntity<Collection<Post>> getAll() {
        var allPosts = service.getAll();
        return ResponseEntity.ok(allPosts);
    }

}
