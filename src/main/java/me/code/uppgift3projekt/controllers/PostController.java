package me.code.uppgift3projekt.controllers;

import lombok.AllArgsConstructor;
import me.code.uppgift3projekt.data.Post;
import me.code.uppgift3projekt.data.User;
import me.code.uppgift3projekt.dto.PostDTO;
import me.code.uppgift3projekt.exception.NotOwnerException;
import me.code.uppgift3projekt.exception.PostAlreadyExistsException;
import me.code.uppgift3projekt.exception.PostDoesNotExistException;
import me.code.uppgift3projekt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService service;

    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody Post post) throws PostAlreadyExistsException {
        var newPost = service.create(post.getCreator(), post.getTitle(), post.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(new PostDTO(newPost.getContent(), newPost.getTitle(), newPost.getCreator().getUsername(), newPost.getCreatedAt()));
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<PostDTO> delete(@RequestBody User user, @PathVariable String title) throws NotOwnerException, PostDoesNotExistException {
        var deletedPost = service.delete(user, title);
        return ResponseEntity.status(HttpStatus.OK).body(new PostDTO(deletedPost.getContent(), deletedPost.getTitle(), deletedPost.getCreator().getUsername(), deletedPost.getCreatedAt()));
    }

    @PutMapping("/{title}")
    public ResponseEntity<PostDTO> edit(@RequestBody Post post, @PathVariable String title) throws NotOwnerException, PostDoesNotExistException {
        var editedPost = service.edit(post.getCreator(), title, post.getContent());
        return ResponseEntity.status(HttpStatus.OK).body(new PostDTO(editedPost.getContent(), editedPost.getTitle(), editedPost.getCreator().getUsername(), post.getCreatedAt()));
    }

    @GetMapping
    public ResponseEntity<Collection<PostDTO>> getAll() {
        var allPosts = service.getAll();
        return ResponseEntity.ok(allPosts.stream().map(post -> new PostDTO(post.getContent(), post.getTitle(), post.getCreator().getUsername(), post.getCreatedAt())).collect(Collectors.toList()));
    }

}
