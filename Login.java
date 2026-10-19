package com.prog5121;

/**
 * PROG5121 Part 1 - Login and Registration Implementation
 */
public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    public Login() {
    }

    public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Checks if username contains an underscore and is no more than 5 characters long.
     */
    public boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkUserName() {
        return checkUserName(this.username);
    }

    /**
     * Checks if password meets complexity rules:
     * - At least 8 characters long
     * - Contains a capital letter
     * - Contains a number
     * - Contains a special character
     */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        String specialChars = "!@#$%^&*()_+-=[]{}|;:'\",.<>?/";

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (specialChars.indexOf(c) >= 0) hasSpecial = true;
        }

        return hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkPasswordComplexity() {
        return checkPasswordComplexity(this.password);
    }

    /**
     * Checks if cell phone number contains international code (+27) and valid format.
     */
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber == null) return false;
        return cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() <= 13;
    }

    public boolean checkCellPhoneNumber() {
        return checkCellPhoneNumber(this.cellPhoneNumber);
    }

    /**
     * Registers user and returns appropriate status message.
     */
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Username successfully captured.";
    }

    /**
     * Verifies login credentials.
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (this.username == null || this.password == null) return false;
        return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
    }

    /**
     * Returns login status message.
     */
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            String fName = (firstName != null) ? firstName : "";
            String lName = (lastName != null) ? lastName : "";
            return "Welcome " + fName + " " + lName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCellPhoneNumber() { return cellPhoneNumber; }
    public void setCellPhoneNumber(String cellPhoneNumber) { this.cellPhoneNumber = cellPhoneNumber; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}
