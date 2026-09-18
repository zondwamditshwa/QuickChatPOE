package quickchat;

import java.util.regex.Pattern;

public class Login {

    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+27\\d{1,10}$");

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String cellPhoneNumber;

    public Login(String firstName, String lastName, String username,
                 String password, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public boolean checkUserName() {
        return username != null
                && username.length() <= 5
                && username.contains("_");
    }

    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasCapital && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber() {
        return cellPhoneNumber != null && CELL_PATTERN.matcher(cellPhoneNumber).matches();
    }

    public String registerUser() {
        boolean usernameOk = checkUserName();
        boolean passwordOk = checkPasswordComplexity();
        boolean cellOk = checkCellPhoneNumber();

        if (usernameOk && passwordOk && cellOk) {
            return "Username successfully captured." + System.lineSeparator()
                    + "Password successfully captured." + System.lineSeparator()
                    + "Cell phone number successfully added." + System.lineSeparator()
                    + "User registered successfully.";
        }

        StringBuilder message = new StringBuilder();
        if (!usernameOk) {
            message.append("Username is not correctly formatted; please ensure that your username ")
                    .append("contains an underscore and is no more than five characters in length.")
                    .append(System.lineSeparator());
        }
        if (!passwordOk) {
            message.append("Password is not correctly formatted; please ensure that the password ")
                    .append("contains at least eight characters, a capital letter, a number, ")
                    .append("and a special character.")
                    .append(System.lineSeparator());
        }
        if (!cellOk) {
            message.append("Cell phone number incorrectly formatted or does not contain international code.")
                    .append(System.lineSeparator());
        }
        return message.toString().trim();
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username != null && username.equals(enteredUsername)
                && password != null && password.equals(enteredPassword);
    }

    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
}
