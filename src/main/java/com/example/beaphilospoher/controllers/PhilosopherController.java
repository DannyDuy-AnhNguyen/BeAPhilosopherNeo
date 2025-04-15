package com.example.beaphilospher.controllers;

public class PhilosopherController extends PersonController {
    private int yearOfBirth;
    private int yearOfDeath;
    private String bio;

    PhilosopherController(int yearOfBirth, int yearOfDeath, String bio, String firstName, String lastName){
        super(firstName, lastName);
        this.yearOfBirth = yearOfBirth;
        this.yearOfDeath = yearOfDeath;
        this.bio = bio;
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
