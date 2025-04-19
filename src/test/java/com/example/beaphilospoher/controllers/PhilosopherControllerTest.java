package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Database.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PhilosopherControllerTest {

    @InjectMocks
    private PhilosopherController philosopherController;

    @Mock
    private DatabaseConnection databaseConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        // Mocking the connection, preparedStatement, and resultSet behavior
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    }

    // Test createPhilosopher()
    @Test
    public void testCreatePhilosopher() throws SQLException {
        // Setting up the data
        philosopherController = new PhilosopherController(1800, 1870, "A philosopher of the 19th century", "John", "Doe");

        // Call the method
        philosopherController.createPhilosopher();

        // Verifying database interaction
        verify(preparedStatement).setString(1, "John");
        verify(preparedStatement).setString(2, "Doe");
        verify(preparedStatement).setInt(3, 1800);
        verify(preparedStatement).setInt(4, 1870);
        verify(preparedStatement).setString(5, "A philosopher of the 19th century");

        verify(preparedStatement).executeUpdate();
    }

    // Test showPhilosophers()
    @Test
    public void testShowPhilosophers() throws SQLException {
        // Mocking resultSet behavior
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("philosopher_id")).thenReturn(1);
        when(resultSet.getString("firstName")).thenReturn("John");
        when(resultSet.getString("lastName")).thenReturn("Doe");
        when(resultSet.getInt("yearOfBirth")).thenReturn(1800);
        when(resultSet.getInt("yearOfDeath")).thenReturn(1870);
        when(resultSet.getString("bio")).thenReturn("A philosopher of the 19th century");

        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Call the method
        List<PhilosopherController> philosophers = PhilosopherController.showPhilosophers();

        // Assertions
        assertNotNull(philosophers);
        assertEquals(1, philosophers.size());
        assertEquals("John", philosophers.get(0).getFirstName());
        assertEquals("Doe", philosophers.get(0).getLastName());
        assertEquals(1800, philosophers.get(0).getYearOfBirth());
        assertEquals(1870, philosophers.get(0).getYearOfDeath());
        assertEquals("A philosopher of the 19th century", philosophers.get(0).getBio());

        // Verifying that the SQL query was executed
        verify(preparedStatement).executeQuery();
    }
}
