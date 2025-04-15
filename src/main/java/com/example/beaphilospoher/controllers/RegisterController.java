package com.example.beaphilospoher.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*; // Import everything needed
import com.example.beaphilospoher.controllers.UserController;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // loads templates/register.html
    }

    @PostMapping("/register")
    public String handleRegistration(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("telephone") String telephone,
            @RequestParam("password") String password,
            @RequestParam("repeatPassword") String repeatPassword, // This line had the wrong name before
            Model model
    ) {

//        Test of this works:
        // Log filled form values (excluding password for safety)
//        System.out.println("First Name: " + firstName);
//        System.out.println("Last Name: " + lastName);
//        System.out.println("Username: " + username);
//        System.out.println("Email: " + email);
//        System.out.println("Telephone: " + telephone);

        // ✅ Password match check
        if (!password.equals(repeatPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }

        // ✅ Register user
        UserController newUser = new UserController(firstName, lastName, username, email, telephone, password);
        newUser.registerUser(newUser);

        return "redirect:/login"; // redirect after success
    }
}
