package com.example.beaphilospoher.Classes;

public class Report {
    private int id;
    private String description;
    private int reported_user;
    private int reported_by;

    Report(int id, String description, int reported_user, int reported_by){
        this.id = id;
        this.description = description;
        this.reported_user = reported_user;
        this.reported_by = reported_by;
    }

    public Report(String description, int reported_user, int reported_by){
        this.description = description;
        this.reported_user = reported_user;
        this.reported_by = reported_by;
    }

    public int getReported_user() {
        return reported_user;
    }
}
