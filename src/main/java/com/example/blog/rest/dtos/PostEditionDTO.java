package com.example.blog.rest.dtos;

import jakarta.validation.constraints.NotBlank;

public record PostEditionDTO(
        @NotBlank
        Long postId,
        @NotBlank
        String text
) {
}
