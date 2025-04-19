package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Database.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @InjectMocks
    private UserController userController;

    @Mock
    private DatabaseConnection databaseConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        // Mock the database connection and prepareStatement
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    }

    // Test the reportUser() method
    @Test
    public void testReportUser() throws SQLException {
        // Create a ReportController object with mock data
        int reportedUserID = 2;
        int reportedByID = 1;
        String reason = "Inappropriate behavior";
        ReportController report = new ReportController(reason, reportedUserID, reportedByID);

        // Call the reportUser method
        userController.reportUser(report, reason, reportedByID);

        // Verify that the preparedStatement's setString and setInt methods are called with the expected values
        verify(preparedStatement).setString(1, reason);
        verify(preparedStatement).setInt(2, reportedUserID);
        verify(preparedStatement).setInt(3, reportedByID);

        // Verify that the executeUpdate() method is called on the preparedStatement to insert the report into the database
        verify(preparedStatement).executeUpdate();
    }

    // Test if the report creation fails with an exception
    @Test
    public void testReportUserWithException() throws SQLException {
        // Simulate a database exception
        doThrow(new SQLException("Database error")).when(preparedStatement).executeUpdate();

        // Create a ReportController object with mock data
        int reportedUserID = 2;
        int reportedByID = 1;
        String reason = "Inappropriate behavior";
        ReportController report = new ReportController(reason, reportedUserID, reportedByID);

        // Call the reportUser method, which should now throw an exception due to the mock SQLException
        assertDoesNotThrow(() -> userController.reportUser(report, reason, reportedByID));

        // Optionally, you can check if the error is logged or the exception is handled as expected
    }
}
