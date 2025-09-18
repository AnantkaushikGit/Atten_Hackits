package com.example.ezattend;

public class HelpercCass {
    String name;
    String userID;  // Change to match the database field name
    String password; // Change to match the database field name

    // Parameterized constructor
    public HelpercCass(String name, String userID, String password) {
        this.name = name;
        this.userID = userID;
        this.password = password;
    }

    // Non-parameterized constructor
    public HelpercCass() {
    }

    // Getters and Setters (updated to match new variable names)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Corrected getter for userID
    public String getUserID() {
        return userID;
    }

    // Corrected setter for userID
    public void setUserID(String userID) {
        this.userID = userID;
    }

    // Corrected getter for password
    public String getPassword() {
        return password;
    }

    // Corrected setter for password
    public void setPassword(String password) {
        this.password = password;
    }
}