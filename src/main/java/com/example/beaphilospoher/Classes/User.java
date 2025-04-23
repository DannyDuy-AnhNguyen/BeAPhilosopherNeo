package com.example.beaphilospoher.Classes;

import com.example.beaphilospoher.Session.UserSession;
import org.mindrot.jbcrypt.BCrypt;
import com.example.beaphilospoher.Database.DatabaseConnection;
//import com.example.beaphilospher.Handlers.Handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User extends Person {
    private int userID;
    private String username;
    private String email;
    private String telephone;
    private String password;

    public User(String firstName, String lastName, String username, String email, String telephone, String password){
        super(firstName, lastName);
        this.username = username;
        this.email = email;
        this.telephone = telephone;
        this.username = username;
        this.password = password;
    }

    // Constructor for Login (only username and password)
    public User(String firstName, String lastName, String username, String password) {
        super(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    //Constructor for showing data
    public User(int userID, String firstName, String lastName, String username, String email, String telephone, Object o){
        super(firstName, lastName);
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.telephone = telephone;
    }


    public void registerUser(User user) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String insertQuery = "INSERT INTO user (firstName, lastName, username, email, password, telephone) VALUES (?, ?, ?, ?, ?, ?)";

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setString(6, telephone);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error registering user.");
        }
    }

    public Boolean login(String username, String password){
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            System.out.println("Username or password is empty!");
            return false;
        }

        DatabaseConnection connectionNow = new DatabaseConnection();
        Connection connectDB = connectionNow.getConnection();

        if (connectDB == null) {
            System.out.println("Database connection failed!");
            return false; // Exit if connection fails
        }

        String verifyLoginQuery = "SELECT user_id, firstname, lastname, username, email, password, telephone FROM user WHERE username = ?";

        try (PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLoginQuery)) {
            preparedStatement.setString(1, username);

            ResultSet queryResult = preparedStatement.executeQuery();

            // If a result is found, proceed with the login
            if (queryResult.next()) {
                // Get the hashed password from the database
                String storedHash = queryResult.getString("password");

                // Verify the entered password against the stored hash using bcrypt
                if (verifyPassword(password, storedHash)) {
                    System.out.println("Login Successful!");

                    int id = queryResult.getInt("user_id");
                    String firstName = queryResult.getString("firstName");
                    String lastName = queryResult.getString("lastName");
                    username = queryResult.getString("username");
                    String email = queryResult.getString("email");
                    String telephone = queryResult.getString("telephone");

                    UserSession.setSession(id, firstName, lastName, username, email, telephone);

                    return true;

                } else {
                    System.out.println("Incorrect password!");
                    // Optionally handle incorrect password case, e.g., show an error to the user
                }
            } else {
                System.out.println("User not found!");
                // Optionally handle the case where the username doesn't exist in the database
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean verifyPassword(String enteredPassword, String storedHash) {
        System.out.println(enteredPassword +"\n"+ storedHash);
        return BCrypt.checkpw(enteredPassword, storedHash);
    }

    public static List<User> showUsers(String currentUsername) {
        List<User> users = new ArrayList<>();
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();

        String query = "SELECT user_id, firstname, lastname, username, email, telephone FROM user WHERE username != ? ORDER BY user_id ASC";

        try (PreparedStatement ps = connectDB.prepareStatement(query)) {
            ps.setString(1, currentUsername);
            ResultSet rs = ps.executeQuery();

            // Loop through the result set and add each user to the list
            while (rs.next()) {
                // Create a new UserController instance for each user
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("telephone"),
                        null
                );

                // Add the user to the list
                System.out.println("User ID: " + user.getUserID()); // Debugging line
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users; // Return the list of users
    }

//    Getters in order to show data
    public int getUserID() {
        return userID;
    }

    public String getFirstname(){
        return firstName;
    }

    public String getLastname(){
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }


    public void reportUser(Report reportedUser, String reason, int userID) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String insertQuery = "INSERT INTO report (description, reported_user_id, reported_by_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, reason);
            preparedStatement.setInt(2, reportedUser.getReported_user());
            preparedStatement.setInt(3, userID);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User reported successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating quote.");
        }
    }

//    Quote
    public void likeQuote(Quote quote){
//        Properly Sql statement and UPDATE function increment plus 1
    }

    public void dislikeQuote(Quote quote){
//        Properly Sql statement and UPDATE function increment plus 1
    }

    public void commentOnQuote(Quote quote, String text){

    }

//    Article
    public void likeArticle(Article article){
//        Properly Sql statement and UPDATE function increment plus 1
}

    public void dislikeArticle(Article article){
//        Properly Sql statement and UPDATE function increment plus 1
    }

    public void commentOnArticle(Article article, String text){

    }
    @Override
    public void displayProfile(){
        System.out.println(
        "Firstname: "+ this.firstName + "\n"+
        "Lastname:"+ this.lastName + "\n"+
        "Username: "+ this.username + "\n"+
        "email: "+ this.email + "\n"+
        "telephone: "+ this.telephone + "\n"+
        "ID-Number: "+ this.id+ "\n"

        );
    }

}
