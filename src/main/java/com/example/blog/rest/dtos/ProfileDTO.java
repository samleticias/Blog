package com.example.blog.rest.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProfileDTO(
        @NotBlank
        String name,
        @NotBlank
        int userId,
        String biography
){
};
