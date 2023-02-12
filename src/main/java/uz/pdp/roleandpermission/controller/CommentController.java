package uz.pdp.roleandpermission.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.roleandpermission.payload.CommentDTO;
import uz.pdp.roleandpermission.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getPostComment(@PathVariable(name = "postId") Long postId) {
        return service.getPostComment(postId);
    }

    @PreAuthorize("isAuthenticated()")
    // aslida bu uslub xato comment va post ro'yhatdan o'tmagan userlar uchun ochiq bo'lishi kerak
    @GetMapping("/{commentId}")
    public ResponseEntity<?> getComment(@PathVariable(name = "commentId") Long commentId) {
        return service.getComment(commentId);
    }


    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody @Valid CommentDTO commentDto) {
        return service.addComment(commentDto);
    }

    @PreAuthorize("hasAuthority('EDIT_POST')")
    @PutMapping("/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable(name = "commentId") Long commentId, @RequestBody @Valid CommentDTO comment) {
        return service.editComment(commentId, comment);
    }

    @PreAuthorize("hasAuthority('DELETE_POST')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "commentId") Long commentId) {
        return service.deleteComment(commentId);
    }
}
