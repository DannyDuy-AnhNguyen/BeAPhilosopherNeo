package com.example.beaphilospoher.controllers;

public class CommentController {
    private int id;
    private String description;
    private PersonController made_by;
    private QuoteController quote;

    CommentController(int id, String description, PersonController made_by, QuoteController quote){
        this.id = id;
        this.description = description;
        this.made_by = made_by;
        this.quote = quote;
    }

    public CommentController getComment(CommentController comment){
        return comment;
    }

    public void display(){

    }
}
