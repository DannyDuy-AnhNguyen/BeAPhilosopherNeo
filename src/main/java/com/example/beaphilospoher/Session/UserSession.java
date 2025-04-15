package com.example.beaphilospoher.Session;

//This class will show the userdata after the user is logged in.
public class UserSession {
    private static UserSession setSession;

    private int id;
    private String firstname;  // Add other fields as needed
    private String lastname;  // Add other fields as needed
    private String username;  // Add other fields as needed
    private String email;
    private String phonenumber;

    private UserSession(int id, String firstname, String lastname, String username, String email, String phonenumber)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    // Singleton instance getter
    public static void setSession(int id, String firstname, String lastname, String username, String email, String phonenumber) {
        if (setSession == null) {
            setSession = new UserSession(id, firstname, lastname, username, email, phonenumber);
            System.out.println("Session created: " + id + ", " + firstname + ", " + lastname + ", " + username + ", " + email + ", " + phonenumber);
        } else if(UserSession.getSession() == null){
            System.out.println("User is not logged in!");
        } else{
            System.out.println("Session has already been created🤓.");
        }
    }

    public static UserSession getSession(){
        return setSession;
    }

    // 💀Clear the session
    public static void clearSession() {
        System.out.println("Session has been cleared🤗!");
        setSession = null;
    }

    public String getFirstname() {
        if (setSession != null) {
        return firstname;
        } else {
            return "No user logged in";  // Return a default message if no session is active
        }
    }

    public String getLastname() {
        if (setSession != null) {
        return lastname;
        } else {
            return "No user logged in";  // Return a default message if no session is active
        }
    }

    public static String getUsername(){
        if (setSession != null) {
            return setSession.username;  // Return the username from the current session
        } else {
            return "No user logged in";  // Return a default message if no session is active
        }
    }

    public String getEmail() {
        if (setSession != null) {
        return email;
        } else {
            return "No user logged in";  // Return a default message if no session is active
        }
    }

    public static String getPhoneNumber(){
        if (setSession != null) {
            return setSession.phonenumber;  // Return the username from the current session
        } else {
            return "No user logged in";  // Return a default message if no session is active
        }
    }

    public static int getID(){
        if (setSession != null) {
            return setSession.id;  // Return the username from the current session
        } else {
            return 0;  // Return a default message if no session is active
        }
    }


    // Optional: Add a toString method for debugging
    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", telefoonnummer='" + phonenumber + '\'' +
                '}';
    }
}

