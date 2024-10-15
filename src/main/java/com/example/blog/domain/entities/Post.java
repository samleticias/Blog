package com.example.blog.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = "postId")
@Entity(name = "posts")
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String postText;
    private LocalDateTime postDate;

    @ManyToOne
    private Profile profile;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<Like> likes;

    @Transient
    private String formattedDate;

    public Post (){
        this.postDate = LocalDateTime.now();
    }

    public int getLikeCount() {
        return likes != null ? likes.size() : 0;
    }
}
