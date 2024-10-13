package com.example.blog.rest.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CommentResponseDTO(
        @NotBlank
        Long commentId,
        @NotBlank
        String text,
        @NotBlank
        String username,
        @NotBlank
        LocalDateTime createdAt
) {
}
