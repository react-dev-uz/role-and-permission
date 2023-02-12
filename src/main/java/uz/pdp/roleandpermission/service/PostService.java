package uz.pdp.roleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.roleandpermission.entity.Post;
import uz.pdp.roleandpermission.payload.PostDTO;
import uz.pdp.roleandpermission.repository.PostRepository;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Service
public class PostService {

    private final String POST_GET_URL = "http://localhost:8080/api/post";

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<?> addPost(PostDTO dto) {
        postRepository.save(new Post(dto.getTitle(), dto.getText(), POST_GET_URL));
        return status(CREATED).body("Post added successfully");
    }

    public ResponseEntity<?> editPost(Long id, PostDTO dto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) return status(NOT_FOUND).body("Post not found");
        Post post = optionalPost.get();
        post.setText(dto.getText());
        post.setTitle(dto.getTitle());
        postRepository.save(post);
        return status(CREATED).body("Post edited successfully");
    }

    public ResponseEntity<?> deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) return status(NOT_FOUND).body("Post not found");
        try {
            postRepository.delete(optionalPost.get());
            return status(NO_CONTENT).body("Post deleted");
        } catch (Exception e) {
            return status(BAD_REQUEST).body("Post could not be deleted");
        }
    }

    public ResponseEntity<?> getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isEmpty()) return status(NOT_FOUND).body("Post not found");
        return ok(optionalPost.get());
    }

    public ResponseEntity<?> getPosts(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page > 0 ? page - 1 : 0, size > 0 ? size : 10);
        return ok(postRepository.findAll(pageRequest));
    }
}
