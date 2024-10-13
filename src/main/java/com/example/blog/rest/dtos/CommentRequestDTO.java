package com.example.blog.rest.dtos;

import jakarta.validation.constraints.NotBlank;

public record CommentRequestDTO(
        @NotBlank
        Long postId,
        @NotBlank
        Long userId,
        @NotBlank
        String text
) {
}
