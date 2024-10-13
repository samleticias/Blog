package com.example.blog.domain.entities;

import com.example.blog.rest.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "userId")
@Entity(name = "users")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
    private LocalDateTime registrationDate;

    public User (UserDTO userDTO){
        this.username = userDTO.username();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.registrationDate = LocalDateTime.now();
    }
}
