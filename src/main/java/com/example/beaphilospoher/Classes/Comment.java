package com.example.beaphilospoher.Classes;

public class Comment {
    private int id;
    private String description;
    private Person made_by;
    private Quote quote;

    Comment(int id, String description, Person made_by, Quote quote){
        this.id = id;
        this.description = description;
        this.made_by = made_by;
        this.quote = quote;
    }

    public Comment getComment(Comment comment){
        return comment;
    }

    public void display(){

    }
}
