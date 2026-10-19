package com.prog5121;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * PROG5121 Formative 2 Part 2 Main Application Driver
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login user = new Login();
        List<Message> messageList = new ArrayList<>();

        System.out.println("=============================================");
        System.out.println(" Welcome to PROG5121 QuickChat Application ");
        System.out.println("=============================================
");

        // Step 1: User Registration
        System.out.println("--- USER REGISTRATION ---");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        user.setFirstName(firstName);

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        user.setLastName(lastName);

        boolean registered = false;
        while (!registered) {
            System.out.print("Enter Username (must contain '_' and <= 5 chars): ");
            String username = scanner.nextLine();
            user.setUsername(username);

            System.out.print("Enter Password (>= 8 chars, 1 uppercase, 1 digit, 1 special char): ");
            String password = scanner.nextLine();
            user.setPassword(password);

            System.out.print("Enter Cell Phone Number (e.g. +27839589762): ");
            String cell = scanner.nextLine();
            user.setCellPhoneNumber(cell);

            String regStatus = user.registerUser();
            System.out.println("Registration Status: " + regStatus);

            if (regStatus.equals("Username successfully captured.")) {
                registered = true;
            } else {
                System.out.println("Please try registering again with correct parameters.
");
            }
        }

        // Step 2: User Login
        System.out.println("
--- USER LOGIN ---");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter Username to Login: ");
            String loginUser = scanner.nextLine();

            System.out.print("Enter Password to Login: ");
            String loginPass = scanner.nextLine();

            loggedIn = user.loginUser(loginUser, loginPass);
            String loginStatus = user.returnLoginStatus(loggedIn);
            System.out.println(loginStatus);
        }

        // Welcome Banner
        System.out.println("
Welcome to QuickChat.
");

        // Step 3: Main Application Menu Loop for Part 2
        boolean quit = false;
        while (!quit) {
            System.out.println("---------------------------------------------");
            System.out.println("MAIN MENU:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Select an option (1-3): ");

            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("How many messages would you like to enter? ");
                    int count = 0;
                    try {
                        count = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                        break;
                    }

                    for (int i = 0; i < count; i++) {
                        System.out.println("
--- Message " + (i + 1) + " ---");
                        System.out.print("Enter Recipient Cell Number (e.g. +27718693002): ");
                        String recipient = scanner.nextLine();

                        System.out.print("Enter Message Text (max 250 characters): ");
                        String text = scanner.nextLine();

                        Message msg = new Message(i, recipient, text, "Pending");

                        String validation = msg.validateMessageLength(text);
                        System.out.println("Validation: " + validation);

                        if (text.length() > 250) {
                            System.out.println("Message is too long and cannot be processed.");
                            continue;
                        }

                        System.out.println("Choose Message Action:");
                        System.out.println("1: Send Message");
                        System.out.println("2: Disregard Message");
                        System.out.println("3: Store Message to send later");
                        System.out.print("Selection: ");

                        int actionChoice = 1;
                        try {
                            actionChoice = Integer.parseInt(scanner.nextLine());
                        } catch (Exception e) {}

                        String statusMsg = msg.sentMessage(actionChoice);
                        System.out.println("Action Result: " + statusMsg);

                        messageList.add(msg);
                        System.out.println("
Message Details:
" + msg.printMessages());
                    }

                    System.out.println("
Total accumulated messages sent: " + Message.returnTotalMessages());
                    break;

                case "2":
                    System.out.println("
--- RECENTLY SENT MESSAGES ---");
                    if (messageList.isEmpty()) {
                        System.out.println("No recent messages to display.");
                    } else {
                        for (Message m : messageList) {
                            System.out.println(m.printMessages());
                            System.out.println("-----------------------------");
                        }
                    }
                    break;

                case "3":
                    quit = true;
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please select 1-3.");
            }
        }
        scanner.close();
    }
}