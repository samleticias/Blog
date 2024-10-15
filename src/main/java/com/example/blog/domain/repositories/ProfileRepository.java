package com.example.blog.domain.repositories;

import com.example.blog.domain.entities.Profile;
import com.example.blog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
    Profile findByUserUsername(String username);
}
