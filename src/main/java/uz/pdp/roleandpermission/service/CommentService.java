package uz.pdp.roleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.roleandpermission.entity.Comment;
import uz.pdp.roleandpermission.entity.Post;
import uz.pdp.roleandpermission.payload.CommentDTO;
import uz.pdp.roleandpermission.repository.CommentRepository;
import uz.pdp.roleandpermission.repository.PostRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public ResponseEntity<?> getPostComment(Long postId) {
        return ok(commentRepository.findAllByPost_Id(postId));
    }

    public ResponseEntity<?> getComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) return status(404).body("Comment not found");
        return ok(optionalComment.get());
    }

    public ResponseEntity<?> addComment(CommentDTO dto) {
        Optional<Post> optionalPost = postRepository.findById(dto.getPostId());
        if (optionalPost.isEmpty()) return status(NOT_FOUND).body("Post not found");
        commentRepository.save(new Comment(dto.getText(), optionalPost.get()));
        return status(CREATED).body("Comment added");
    }

    public ResponseEntity<?> editComment(Long commentId, CommentDTO dto) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isEmpty()) return status(NOT_FOUND).body("Comment not found");
        Optional<Post> optionalPost = postRepository.findById(dto.getPostId());
        if (optionalPost.isEmpty()) return status(NOT_FOUND).body("Post not found");
        Comment comment = optionalComment.get();
        comment.setPost(optionalPost.get());
        comment.setText(dto.getText());
        return ok("Comment edited");
    }

    public ResponseEntity<?> deleteComment(Long commentId) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        return ok("Comment deleted");
    }
}
