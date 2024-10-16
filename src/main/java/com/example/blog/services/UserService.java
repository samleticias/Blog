package com.example.blog.services;

import com.example.blog.domain.entities.Profile;
import com.example.blog.domain.entities.User;
import com.example.blog.domain.repositories.ProfileRepository;
import com.example.blog.domain.repositories.UserRepository;
import com.example.blog.rest.dtos.UserDTO;
import com.example.blog.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

    public User createUser(UserDTO userDTO) {
        String hashedPassword = BCrypt.hashpw(userDTO.password(), BCrypt.gensalt());
        User user = new User(userDTO, hashedPassword);
        this.saveUser(user);
        return user;
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && checkPassword(password, user.getPassword())) {
            System.out.println("Login realizado com sucesso!");
            return user;
        }
        return null;
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public Optional<Profile> getProfileByUser(User user) {
        return profileRepository.findByUser(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("User informed not found");

        userRepository.delete(user.get());
    }

    public User updateUser(Long id, UserDTO userDTO) throws UserNotFoundException {
        User user = this.getUserById(id);
        user.setEmail(userDTO.email());
        user.setUsername(userDTO.username());

        if (userDTO.password() != null && !userDTO.password().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(userDTO.password(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
        }

        this.saveUser(user);
        return user;
    }

}
