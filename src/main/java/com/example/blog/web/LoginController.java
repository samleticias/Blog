package com.example.blog.web;

import com.example.blog.domain.entities.Post;
import com.example.blog.domain.entities.Profile;
import com.example.blog.domain.entities.User;
import com.example.blog.services.PostService;
import com.example.blog.services.ProfileService;
import com.example.blog.services.UserService;
import com.example.blog.services.exceptions.PostNotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PostService postService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            String redirectUrl = "redirect:/profile/" + user.getUsername();
            System.out.println("Redirecionando para: " + redirectUrl);
            return redirectUrl;
        } else {
            model.addAttribute("error", "Nome de usuário ou senha inválidos.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/profile/{username}")
    public String viewProfile(@PathVariable String username, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null && user.getUsername().equals(username)) {
            Optional<Profile> profile = userService.getProfileByUser(user);
            if (profile.isPresent()) {
                model.addAttribute("profile", profile.get());

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                profile.get().getPostList().forEach(post -> {
                    String formattedDate = post.getPostDate().format(formatter);
                    post.setFormattedDate(formattedDate);
                });

                return "profile";
            } else {
                model.addAttribute("error", "Perfil não encontrado.");
                return "error";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/comments/{postId}")
    public String showComments(@PathVariable Long postId, Model model) throws PostNotFoundException {
        Post post = postService.getPostById(postId);
        if (post != null) {
            model.addAttribute("post", post);
            return "comments";
        }
        return "redirect:/error";
    }

}
