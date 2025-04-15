package com.example.beaphilospher.controllers;

import com.example.beaphilospher.controllers.PersonController;

public class ReportController {
    private int id;
    private String description;
    private PersonController reported_user;
    private UserController reported_by;

    ReportController(int id, String description, PersonController reported_user, UserController reported_by){
        this.id = id;
        this.description = description;
        this.reported_user = reported_user;
        this.reported_by = reported_by;
    }
}
