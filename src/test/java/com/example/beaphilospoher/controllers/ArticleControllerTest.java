package com.example.beaphilospoher.controllers;

import com.example.beaphilospoher.Database.DatabaseConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

public class ArticleControllerTest {

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private DatabaseConnection databaseConnection;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);

        // Mocking the connection and preparedStatement behavior
        when(databaseConnection.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);

        // Manually instantiate ArticleController with necessary parameters
        articleController = new ArticleController("Test Title", "Science", "Some article text", 10, 2, 1);
    }

    @Test
    public void testCreateArticle() throws SQLException {
        // Calling the method
        articleController.createArticle();

        // Verifying the database interaction
        verify(preparedStatement).setString(1, "Test Title");
        verify(preparedStatement).setString(2, "Science");
        verify(preparedStatement).setString(3, "Some article text");
        verify(preparedStatement).setInt(4, 10);
        verify(preparedStatement).setInt(5, 2);
        verify(preparedStatement).setInt(6, 1);

        verify(preparedStatement).executeUpdate();
    }
}
