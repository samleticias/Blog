package com.example.blog.web;

import com.example.blog.rest.dtos.UserDTO;
import com.example.blog.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserViewController {

    private static final Logger logger = LoggerFactory.getLogger(UserViewController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO("", "", ""));
        return "user-form";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
        if (userDTO.password() == null || userDTO.password().isEmpty()) {
            model.addAttribute("error", "A senha é obrigatória.");
            return "user-form";
        }

        try {
            userService.createUser(userDTO);
            return "redirect:/user/success";
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("username")) {
                model.addAttribute("error", "Nome de usuário ou email já foram cadastrados. Tente novamente.");
            } else {
                model.addAttribute("error", "Erro ao cadastrar usuário. Por favor, tente novamente.");
            }
            return "user-form";
        }
    }

    @GetMapping("/success")
    public String registrationSuccess() {
        return "success";
    }
}