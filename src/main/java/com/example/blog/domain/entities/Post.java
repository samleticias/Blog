package com.example.blog.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

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

    public Post (){
        this.postDate = LocalDateTime.now();
    }
}
