package com.example.beaphilospoher.Classes;

abstract public class Person {
    protected int id;
    protected String firstName;
    protected String lastName;

    public Person(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName; 
    }

    public void displayProfile(){
        System.out.println("Firstname: "+ firstName + "\n"+
                           "Lastname:"+ lastName + "\n"+
                            "ID-Number: "+ id);
    }
}
