package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Classes.User;
import org.springframework.ui.Model;
import com.example.beaphilospoher.Session.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
            )
    {
        User user = new User(firstName, lastName, username, password);
        user.login(username, password);

        // Example logic
        boolean success = user.login(username, password); // your login method

        if (success) {
            // Retrieve user session details
            UserSession session = UserSession.getSession();

            // Add user data to the model for Thymeleaf
            model.addAttribute("username", session.getUsername());
            model.addAttribute("firstName", session.getFirstname());
            model.addAttribute("lastName", session.getLastname());

            return "home"; // return the template name (home.html)
        } else {
            model.addAttribute("error", "Login failed");
            return "login";
        }
    }

    @GetMapping("logout")
    public String logout(){
        UserSession session = UserSession.getSession();
        session.clearSession();
        return "redirect:/";
    }
}


