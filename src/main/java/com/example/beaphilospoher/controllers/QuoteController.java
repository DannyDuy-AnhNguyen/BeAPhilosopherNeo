package com.example.beaphilospher.controllers;

public class QuoteController {
    private int id;
    private String text;
    private int like;
    private int dislike;
    private PersonController author;

    public QuoteController(int id, String text, int like, int dislike, PersonController author){
        this.id = id;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.author = author;
    }

    public void addLike(UserController user){

    }

    public void addDislike(UserController user){

    }

    public void addComment(CommentController comment){

    }
}
