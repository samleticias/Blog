package com.example.blog.rest.dtos;

import jakarta.validation.constraints.NotBlank;

public record LikeRequestDTO(
        @NotBlank
        Long userId
) {
}
