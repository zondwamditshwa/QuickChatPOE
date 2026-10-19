package com.prog5121;

import java.util.Random;

/**
 * PROG5121 Part 2 - Message Implementation
 */
public class Message {
    private static int totalMessageCount = 0;

    private String messageID;
    private int numMessagesSent;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private String flag; // "Sent", "Disregarded", "Stored"

    public Message() {
    }

    public Message(int numMessagesSent, String recipientCell, String messageText, String flag) {
        this.numMessagesSent = numMessagesSent;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.flag = flag;
        this.messageID = generateRandomMessageID();
        this.messageHash = createMessageHash(this.messageID, this.numMessagesSent, this.messageText);
        totalMessageCount++;
    }

    public Message(String messageID, int numMessagesSent, String recipientCell, String messageText, String messageHash, String flag) {
        this.messageID = messageID;
        this.numMessagesSent = numMessagesSent;
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageHash = messageHash;
        this.flag = flag;
        totalMessageCount++;
    }

    /**
     * Generates a 10-digit random Message ID.
     */
    public static String generateRandomMessageID() {
        Random rand = new Random();
        long num = (long) (rand.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(num);
    }

    /**
     * Checks if Message ID is non-null and no more than 10 characters long.
     */
    public boolean checkMessageID(String id) {
        return id != null && id.length() <= 10;
    }

    public boolean checkMessageID() {
        return checkMessageID(this.messageID);
    }

    /**
     * Checks if recipient cell phone number is valid (contains international code +27).
     */
    public boolean checkRecipientCell(String recipient) {
        if (recipient == null) return false;
        return recipient.startsWith("+27") && recipient.length() <= 13;
    }

    public boolean checkRecipientCell() {
        return checkRecipientCell(this.recipientCell);
    }

    /**
     * Creates Message Hash format:
     * First two digits of message ID, colon, message sequence number, colon, first and last word in ALL CAPS.
     * Example: 00:0:HITONIGHT
     */
    public String createMessageHash(String messageId, int msgNum, String message) {
        if (messageId == null || messageId.length() < 2 || message == null || message.trim().isEmpty()) {
            return "00:0:INVALID";
        }

        String idPrefix = messageId.substring(0, 2);
        String cleaned = message.replaceAll("[^a-zA-Z0-9\\s]", "").trim();
        String[] words = cleaned.split("\\s+");

        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        return idPrefix + ":" + msgNum + ":" + firstWord + lastWord;
    }

    /**
     * Returns confirmation message according to user's option selection:
     * 1: Send Message -> "Message successfully sent"
     * 2: Disregard Message -> "Press 0 to delete the message"
     * 3: Store Message -> "Message successfully stored"
     */
    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                this.flag = "Sent";
                return "Message successfully sent";
            case 2:
                this.flag = "Disregarded";
                return "Press 0 to delete the message";
            case 3:
                this.flag = "Stored";
                return "Message successfully stored";
            default:
                return "Invalid choice";
        }
    }

    /**
     * Checks if message length is within 250 characters.
     */
    public String validateMessageLength(String text) {
        if (text == null) return "Message is empty.";
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int overflow = text.length() - 250;
            return "Message exceeds 250 characters by " + overflow + " characters; please reduce the size.";
        }
    }

    /**
     * Formats details for display.
     */
    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipientCell + "\n" +
               "Message: " + messageText + "\n" +
               "Status Flag: " + flag;
    }

    /**
     * Returns accumulated message count.
     */
    public static int returnTotalMessages() {
        return totalMessageCount;
    }

    public static void resetTotalMessages() {
        totalMessageCount = 0;
    }

    /**
     * Convert message to JSON string format.
     */
    public String toJSONString() {
        return "{" +
               "\"messageID\":\"" + messageID + "\"," +
               "\"numMessagesSent\":" + numMessagesSent + "," +
               "\"recipientCell\":\"" + recipientCell + "\"," +
               "\"messageText\":\"" + escapeJSON(messageText) + "\"," +
               "\"messageHash\":\"" + messageHash + "\"," +
               "\"flag\":\"" + flag + "\"" +
               "}";
    }

    private String escapeJSON(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    // Getters and Setters
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }

    public int getNumMessagesSent() { return numMessagesSent; }
    public void setNumMessagesSent(int numMessagesSent) { this.numMessagesSent = numMessagesSent; }

    public String getRecipientCell() { return recipientCell; }
    public void setRecipientCell(String recipientCell) { this.recipientCell = recipientCell; }

    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }

    public String getMessageHash() { return messageHash; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }

    public String getFlag() { return flag; }
    public void setFlag(String flag) { this.flag = flag; }
}
