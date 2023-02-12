package uz.pdp.roleandpermission.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import uz.pdp.roleandpermission.payload.PostDTO;
import uz.pdp.roleandpermission.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Secured("ADD_POST")
    @PostMapping
    public ResponseEntity<?> addPost(@RequestBody @Valid PostDTO postDTO) {
        return postService.addPost(postDTO);
    }

    @Secured("EDIT_POST")
    @PutMapping("/{postId}")
    public ResponseEntity<?> editPost(@PathVariable(name = "postId") Long postId, @RequestBody @Valid PostDTO postDTO) {
        return postService.editPost(postId, postDTO);
    }

    @Secured("DELETE_POST")
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "postId") Long postId) {
        return postService.deletePost(postId);
    }

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size) {
        return postService.getPosts(page, size);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable(name = "postId") Long postId) {
        return postService.getPost(postId);
    }
}
