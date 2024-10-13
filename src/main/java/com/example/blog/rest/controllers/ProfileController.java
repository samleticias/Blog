package com.example.blog.rest.controllers;

import com.example.blog.domain.entities.Profile;
import com.example.blog.rest.dtos.ProfileDTO;
import com.example.blog.services.ProfileService;
import com.example.blog.services.exceptions.ProfileNotFoundException;
import com.example.blog.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles(){
        return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Long id) throws ProfileNotFoundException {
        return new ResponseEntity<>(profileService.getProfileById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@RequestBody ProfileDTO profileDTO) throws UserNotFoundException {
        return new ResponseEntity<>(profileService.createProfile(profileDTO), HttpStatus.CREATED);
    }
}

