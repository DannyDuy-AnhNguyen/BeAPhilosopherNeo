package com.example.beaphilospher.controllers;

abstract public class PersonController {
    protected int id;
    protected String firstName;
    protected String lastName;

    public PersonController(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName; 
    }

    public void displayProfile(){
        System.out.println("Firstname: "+ firstName + "\n"+
                           "Lastname:"+ lastName + "\n"+
                            "ID-Number: "+ id);
    }
}
