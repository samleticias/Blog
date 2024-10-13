package com.example.blog.rest.dtos;

import jakarta.validation.constraints.NotBlank;

public record PostDTO(
        @NotBlank
        Long profileId,

        @NotBlank
        String text
) {
}
