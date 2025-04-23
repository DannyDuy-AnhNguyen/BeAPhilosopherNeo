package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Session.UserSession;
import com.example.beaphilospoher.Classes.Philosopher;
import com.example.beaphilospoher.Classes.Quote;
import com.example.beaphilospoher.Classes.Report;
import com.example.beaphilospoher.Classes.User;
import com.example.beaphilospoher.Classes.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TransferController {
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

        User user = new User(null, null, null, null);
        // Get the list of all users (excluding the current one) and add it to the model
        List<User> users = User.showUsers(session.getUsername());
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

        User user = new User(null, null, null, null);
        // Get the list of all users (excluding the current one) and add it to the model
        List<User> users = User.showUsers(session.getUsername());
        model.addAttribute("users", users);
        System.out.println(users);
        return "report";
    }

    @PostMapping("/report")
    public String report(
            @RequestParam int userID,
            @RequestParam String reportReason,
            Model model
    ){
        UserSession session = UserSession.getSession();

        // Add user data to the model for Thymeleaf
        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        User user = new User(null, null, null, null);
        Report report = new Report(reportReason, userID, session.getID());
        user.reportUser(report, reportReason, session.getID());

        return "redirect:/home";
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

        Philosopher newPhilosopher = new Philosopher(yearOfBirth, yearOfDeath, bio, firstName, lastName);
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

        Philosopher philosopher = new Philosopher(0, 0, null, null, null);
        List<Philosopher> philosophers = Philosopher.showPhilosophers();
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

        Philosopher philosopher = new Philosopher(0, 0, null, null, null);
        List<Philosopher> philosophers = Philosopher.showPhilosophers();
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

        Quote newQuote = new Quote(textQuote, 0, 0, session.getID());
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

        Quote newQuote = new Quote(textQuote, 0, 0, session.getID());
        newQuote.createQuote(textQuote);

        return "redirect:/home";
    }

    @GetMapping("/showQuote")
    public String showQuote(Model model){
        UserSession session = UserSession.getSession();

        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        Quote quote = new Quote(null, 0, 0, session.getID());
        List<Quote> quotes = Quote.showQuote();
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

        Article newArticle = new Article(title, branch, articleText, 0, 0, session.getID());
        newArticle.createArticle();

        return "redirect:/home";
    }

    @GetMapping("/showArticle")
    public String showArticle(Model model){
        UserSession session = UserSession.getSession();

        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

        Article article = new Article(null, null, null, 0, 0, session.getID());
        List<Article> articles = Article.showArticle();
        model.addAttribute("articles", articles);

        return "showArticle";
    }

    @GetMapping("/readAnArticle")
    public String readAnArticle(@RequestParam("id") int articleId, Model model) {
        UserSession session = UserSession.getSession();

        model.addAttribute("username", session.getUsername());
        model.addAttribute("firstName", session.getFirstname());
        model.addAttribute("lastName", session.getLastname());

//        ArticleController article = ArticleController.getArticleById(articleId); // You need to create this method
//        model.addAttribute("article", article);

        return "readAnArticle";
    }


}
