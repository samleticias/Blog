package com.example.blog.rest.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        String username,
        @NotBlank
        String password,
        @NotBlank
        String email
) {
}
