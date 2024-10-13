package com.example.blog.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "profiles")
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String name;

    @Column(length = 300)
    private String biography;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private List<Post> postList;
}
