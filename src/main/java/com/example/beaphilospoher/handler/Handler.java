package com.example.beaphilospoher.handler;
import java.util.ArrayList;
import java.util.Scanner;

public class Handler {
    public ArrayList<String> enterRegister(){
        Scanner scanner = new Scanner(System.in);
        //                    Registering an account: Firstname
        System.out.println("What is your firstname?");
        String command = scanner.nextLine();
        String firstname = command;

//                    Registering an account: Lastname
        System.out.println("What is your lastname?");
        command = scanner.nextLine();
        String lastname = command;

//                    Registering an account: username
        System.out.println("What is your username?");
        command = scanner.nextLine();
        String username = command;

//                    Registering an account: email
        System.out.println("What is your email?");
        command = scanner.nextLine();
        String email = command;

//                    Registering an account: telephone
        System.out.println("What is your telephone number?");
        command = scanner.nextLine();
        String telephone = command;

//                    Registering an account: email
        System.out.println("What will be your password?");
        command = scanner.nextLine();
        String password = command;

        ArrayList<String> result = new ArrayList<>();
        result.add(firstname);
        result.add(lastname);
        result.add(username);
        result.add(email);
        result.add(telephone);
        result.add(password);

        return result;
    }

    public ArrayList<String> enterLogin(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What is your username?");
        String command = scanner.nextLine();
        String username = command;

        System.out.println("What will be your password?");
        command = scanner.nextLine();
        String password = command;

        ArrayList<String> result = new ArrayList<>();
        result.add(username);
        result.add(password);

        return result;
    }
}
