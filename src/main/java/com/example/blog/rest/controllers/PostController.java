package com.example.blog.rest.controllers;

import com.example.blog.domain.entities.Comment;
import com.example.blog.domain.entities.Post;
import com.example.blog.rest.dtos.CommentRequestDTO;
import com.example.blog.rest.dtos.CommentResponseDTO;
import com.example.blog.rest.dtos.LikeRequestDTO;
import com.example.blog.rest.dtos.PostDTO;
import com.example.blog.services.PostService;
import com.example.blog.services.exceptions.PostNotFoundException;
import com.example.blog.services.exceptions.ProfileNotFoundException;
import com.example.blog.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) throws PostNotFoundException {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) throws ProfileNotFoundException {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> addComment(@PathVariable Long postId, @RequestBody CommentRequestDTO commentRequestDTO) throws UserNotFoundException, PostNotFoundException {
        CommentResponseDTO responseDTO = postService.addCommentToPost(postId, commentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id) throws PostNotFoundException {
        postService.deletePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long postId, @RequestBody LikeRequestDTO likeRequestDTO) throws UserNotFoundException, PostNotFoundException {
        postService.likePost(postId, likeRequestDTO.userId());
        return ResponseEntity.ok().build();
    }


}
