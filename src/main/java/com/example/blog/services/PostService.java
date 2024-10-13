package com.example.blog.services;

import com.example.blog.domain.entities.Comment;
import com.example.blog.domain.entities.Post;
import com.example.blog.domain.entities.Profile;
import com.example.blog.domain.entities.User;
import com.example.blog.domain.repositories.CommentRepository;
import com.example.blog.domain.repositories.PostRepository;
import com.example.blog.domain.repositories.UserRepository;
import com.example.blog.rest.dtos.CommentRequestDTO;
import com.example.blog.rest.dtos.CommentResponseDTO;
import com.example.blog.rest.dtos.PostDTO;
import com.example.blog.services.exceptions.PostNotFoundException;
import com.example.blog.services.exceptions.ProfileNotFoundException;
import com.example.blog.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileService profileService;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public void savePost(Post post) {
        this.postRepository.save(post);
    }

    public Post getPostById(Long id) throws PostNotFoundException {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(("Post not found.")));
    }

    public Post createPost(PostDTO postDTO) throws ProfileNotFoundException {
        Post post = new Post();
        post.setPostText(postDTO.text());

        Profile profile = profileService.getProfileById(postDTO.profileId());
        post.setProfile(profile);

        this.savePost(post);
        return post;
    }

    public CommentResponseDTO addCommentToPost(Long postId, CommentRequestDTO commentRequestDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(commentRequestDTO.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setText(commentRequestDTO.text());
        comment.setPost(post);
        comment.setUser(user);
        comment.setUsername(user.getUsername());
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return new CommentResponseDTO(
                comment.getCommentId(),
                comment.getText(),
                comment.getUsername(),
                comment.getCreatedAt()
        );
    }

}
