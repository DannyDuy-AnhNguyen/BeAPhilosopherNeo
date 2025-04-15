package com.example.beaphilospher.controllers;

public class ArticleController {
    private int article;
    private String branch;
    private String approach;
    private String text;
    private int like;
    private int dislike;
    private PersonController author;

    ArticleController(int article, String branch, String approach, String text, int like, int dislike, PersonController author){
        this.article = article;
        this.branch = branch;
        this.approach = approach;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.author = author;
    }

    public void displaySummary(){

    }

//    For article will come later. First quotes😉:
public void addLike(UserController user){

}

    public void addDislike(UserController user){

    }

    public void addComment(CommentController comment){

    }
}
