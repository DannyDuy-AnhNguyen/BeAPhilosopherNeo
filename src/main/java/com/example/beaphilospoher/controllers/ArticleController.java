package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {
    private int article;
    private String title;
    private String branch;
    private String text;
    private int like;
    private int dislike;
    private int author;
    private String username;

    public ArticleController(String title, String branch, String text, int like, int dislike, int author){
        this.title = title;
        this.branch = branch;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.author = author;
    }

//    show all data
    public ArticleController(int article, String title, String branch, String text, int like, int dislike, String username){
        this.article = article;
        this.title = title;
        this.branch = branch;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.username = username;
    }

    public void displaySummary(){

    }

    public void createArticle(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String insertQuery = "INSERT INTO article (title, branch, appearance, likes, dislikes, author_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, this.title);
            preparedStatement.setString(2, this.branch);
            preparedStatement.setString(3, this.text);
            preparedStatement.setInt(4, this.like);
            preparedStatement.setInt(5, this.dislike);
            preparedStatement.setInt(6, this.author);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating article.");
        }
    }

    public static List<ArticleController> showArticle(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        List<ArticleController> articles = new ArrayList<>();
        String selectQuery = "SELECT article_id, title, branch, appearance, likes, dislikes, username FROM article\n" +
                "JOIN user ON author_id = user_id;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                ArticleController article = new ArticleController(
                        rs.getInt("article_id"),
                        rs.getString("title"),
                        rs.getString("branch"),
                        rs.getString("appearance"),
                        rs.getInt("likes"),
                        rs.getInt("dislikes"),
                        rs.getString("username")
                );
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    public int getArticle() {
        return article;
    }

    public String getTitle() {
        return title;
    }

    public String getBranch() {
        return branch;
    }

    public String getText(){
        return text;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public String getUsername() {
        return username;
    }

    //    For article will come later. First quotes😉:
    public void addLike(UserController user){

    }

    public void addDislike(UserController user){

    }

    public void addComment(CommentController comment){

    }
}
