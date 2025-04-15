package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhilosopherController extends PersonController {
    private int philosopherId;
    private int yearOfBirth;
    private int yearOfDeath;
    private String bio;

    PhilosopherController(int yearOfBirth, int yearOfDeath, String bio, String firstName, String lastName){
        super(firstName, lastName);
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
        this.bio = bio;
    }

    PhilosopherController(int philosopherId, String firstName, String lastName, int yearOfBirth, int yearOfDeath, String bio){
        super(firstName, lastName);
        this.philosopherId = philosopherId;
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
        this.bio = bio;
    }

    public void createPhilosopher() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String insertQuery = "INSERT INTO philosopher (firstName, lastName, yearOfBirth, yearOfDeath, bio) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, yearOfBirth);
            preparedStatement.setInt(4, yearOfDeath);
            preparedStatement.setString(5, bio);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User registered successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating philosopher.");
        }
    }

    public static List<PhilosopherController> showPhilosophers() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        List<PhilosopherController> philosophers = new ArrayList<>();
        String selectQuery = "SELECT philosopher_id, firstName, lastName, yearOfBirth, yearOfDeath, bio FROM philosopher";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                PhilosopherController philosopher = new PhilosopherController(
                        rs.getInt("philosopher_id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("yearOfBirth"),
                        rs.getInt("yearOfDeath"),
                        rs.getString("bio")
                );
                philosophers.add(philosopher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return philosophers;
    }

    public int getPhilosopherId(){
        return philosopherId;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getYearOfDeath() {
        return yearOfDeath;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public void displayProfile(){
        System.out.println("Firstname: "+ firstName + "\n"+
                "Lastname:"+ lastName + "\n"+
                "ID-Number: "+ id + "\n"+
                "Year of Birth: "+ yearOfBirth + "\n"+
                "Year of Death: "+ yearOfDeath + "\n"+
                "Bio: "+ bio + "\n");
    }
}
