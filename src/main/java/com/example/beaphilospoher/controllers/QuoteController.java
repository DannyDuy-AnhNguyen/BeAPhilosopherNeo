package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuoteController {
    private int id;
    private String text;
    private int like;
    private int dislike;
    private int author;
    private String username;

    public QuoteController(String text, int like, int dislike, int author){
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.author = author;
    }

    //Show all data
    public QuoteController(int id, String text, int like, int dislike, String username){
        this.id = id;
        this.text = text;
        this.like = like;
        this.dislike = dislike;
        this.username = username;
    }

    public void createQuote(String quote) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String insertQuery = "INSERT INTO quote (text, likes, dislikes, author_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, quote);
            preparedStatement.setInt(2, this.like);
            preparedStatement.setInt(3, this.dislike);
            preparedStatement.setInt(4, this.author);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating quote.");
        }
    }

    public void createQuote2(String quote, int philosopherID) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String insertQuery = "INSERT INTO quote (text, likes, dislikes, author_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, quote);
            preparedStatement.setInt(2, this.like);
            preparedStatement.setInt(3, this.dislike);
            preparedStatement.setInt(4, philosopherID);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("philosopher's quote created successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating quote.");
        }
    }

    public static List<QuoteController> showQuote() {
        List<QuoteController> quotes = new ArrayList<>();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String selectQuery = "SELECT quote_id, q.text, q.likes, q.dislikes, u.username FROM quote q\n" +
                "JOIN user u ON q.author_id = u.user_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                QuoteController quote = new QuoteController(
                        rs.getInt("quote_id"),
                        rs.getString("text"),
                        rs.getInt("likes"),
                        rs.getInt("dislikes"),
                        rs.getString("username")
                );
                quotes.add(quote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quotes;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }

    public int getAuthor() {
        return author;
    }

    public String getUsername() {
        return username;
    }


    public void addLike(UserController user){

    }

    public void addDislike(UserController user){

    }

    public void addComment(CommentController comment){

    }
}
