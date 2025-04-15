package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Session.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model){
        UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        return "home";
    }

    @GetMapping("/user")
    public String user(Model model){
          UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        UserController user = new UserController(null, null, null, null);
        // Get the list of all users (excluding the current one) and add it to the model
        List<UserController> users = UserController.showUsers(session.getUsername());
        model.addAttribute("users", users);
        System.out.println(users);
        return "user";
    }

    @GetMapping("/report")
    public String report(Model model){
          UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());
        return "report";
    }

    @GetMapping("/createPhilosopher")
    public String createPhilosopher(Model model){
          UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());
        return "createPhilosopher";
    }

    @PostMapping("/createPhilosopher")
    public String handelCreatePhilsopher(
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("yearOfBirth") int yearOfBirth,
            @RequestParam("yearOfDeath") int yearOfDeath,
            @RequestParam("bio") String bio,
            Model model
    ){
        UserSession session = UserSession.getSession();
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        PhilosopherController newPhilosopher = new PhilosopherController(yearOfBirth, yearOfDeath, bio, firstName, lastName);
        newPhilosopher.createPhilosopher();

        return "redirect:/home";
    }

    @GetMapping("/showPhilosopher")
    public String showPhilosopher(Model model){
        UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        PhilosopherController philosopher = new PhilosopherController(0, 0, null, null, null);
        List<PhilosopherController> philosophers = PhilosopherController.showPhilosophers();
        model.addAttribute("philosophers", philosophers);


        return "showPhilosopher";
    }

    @GetMapping("/addQuoteOfPhilosopher")
    public String addQuoteOfPhilosopher(Model model){
        UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        PhilosopherController philosopher = new PhilosopherController(0, 0, null, null, null);
        List<PhilosopherController> philosophers = PhilosopherController.showPhilosophers();
        model.addAttribute("philosophers", philosophers);

        return "addQuoteOfPhilosopher";
    }

    @PostMapping("/addQuoteOfPhilosopher")
    public String postAddQuoteOfPhilosopher(
            @RequestParam("philosopherId") int philosopherId,
            @RequestParam("textQuote") String textQuote,
            Model model
    ){
        System.out.println("🤗:"+ philosopherId);
        UserSession session = UserSession.getSession();

        QuoteController newQuote = new QuoteController(textQuote, 0, 0, session.getID());
        newQuote.createQuote2(textQuote, philosopherId);

        return "redirect:/home";
    }

    @GetMapping("/createQuote")
    public String quote(Model model){
          UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());
        return "createQuote";
    }

    @PostMapping("/createQuote")
    public String postQuote(
            @RequestParam("textQuote") String textQuote,
            Model model
    ){
        System.out.println("🤗:"+ textQuote);
        UserSession session = UserSession.getSession();

        QuoteController newQuote = new QuoteController(textQuote, 0, 0, session.getID());
        newQuote.createQuote(textQuote);

        return "redirect:/home";
    }

    @GetMapping("/showQuote")
    public String showQuote(Model model){
        UserSession session = UserSession.getSession();

        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        QuoteController quote = new QuoteController(null, 0, 0, session.getID());
        List<QuoteController> quotes = QuoteController.showQuote();
        model.addAttribute("quotes", quotes);


        return "showQuote";
    }

    @GetMapping("/createArticle")
    public String article(Model model){
          UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());
        return "createArticle";
    }

    @PostMapping("/createArticle")
    public String postArticle(
            @RequestParam("title") String title,
            @RequestParam("branch") String branch,
            @RequestParam("articleText") String articleText,
            Model model
    ){
        System.out.println("🤗"+ title);
        System.out.println("🤗"+ branch);
        System.out.println("🤗"+ articleText);

        UserSession session = UserSession.getSession();

        ArticleController newArticle = new ArticleController(title, branch, articleText, 0, 0, session.getID());
        newArticle.createArticle();

        return "redirect:/home";
    }

    @GetMapping("/showArticle")
    public String showArticle(Model model){
        UserSession session = UserSession.getSession();

        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        ArticleController article = new ArticleController(null, null, null, 0, 0, session.getID());
        List<ArticleController> articles = ArticleController.showArticle();
        model.addAttribute("articles", articles);

        return "showArticle";
    }
}
