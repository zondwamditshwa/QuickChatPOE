package quickchat;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Message {

    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+27\\d{1,10}$");
    private static final int MAX_MESSAGE_LENGTH = 250;
    private static final String JSON_FILE = "messages.json";

    private static int totalMessagesSent = 0;
    private static final List<Message> sentMessageLog = new ArrayList<>();

    private final String messageID;
    private final String recipient;
    private final String messageText;
    private String messageHash;
    private int messageNumber = -1;

    public Message(String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageText = messageText;
    }

    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && CELL_PATTERN.matcher(recipient).matches()) {
            return "Recipient cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }

    public String checkMessageLength() {
        if (messageText != null && messageText.length() <= MAX_MESSAGE_LENGTH) {
            return "Message sent.";
        }
        return "Please enter a message of less than 250 characters.";
    }

    public String createMessageHash() {
        String idPrefix = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        messageHash = (idPrefix + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
        return messageHash;
    }

    public String sendMessage(String choice) {
        if (choice == null) {
            return "Invalid option selected.";
        }

        switch (choice.trim().toLowerCase()) {
            case "send":
                if (!checkMessageLength().equals("Message sent.")) {
                    return checkMessageLength();
                }
                messageNumber = totalMessagesSent;
                totalMessagesSent++;
                createMessageHash();
                sentMessageLog.add(this);
                return "Message successfully sent.";
            case "store":
                return storeMessage();
            case "disregard":
                return "Press 0 to delete the message.";
            default:
                return "Invalid option selected.";
        }
    }

    public static String printMessages() {
        if (sentMessageLog.isEmpty()) {
            return "No messages have been sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessageLog) {
            sb.append(m.printMessageDetails())
                    .append(System.lineSeparator())
                    .append("-----")
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String printMessageDetails() {
        return "Message ID: " + messageID + System.lineSeparator()
                + "Message Hash: " + messageHash + System.lineSeparator()
                + "Recipient: " + recipient + System.lineSeparator()
                + "Message: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    public String storeMessage() {
        if (messageHash == null) {
            createMessageHash();
        }
        String entry = String.format(
                "{\"messageID\": \"%s\", \"messageHash\": \"%s\", \"recipient\": \"%s\", \"message\": \"%s\"}",
                messageID, messageHash, recipient, escapeJson(messageText));

        try {
            File file = new File(JSON_FILE);
            List<String> existingEntries = new ArrayList<>();

            if (file.exists()) {
                for (String line : Files.readAllLines(file.toPath())) {
                    String trimmed = line.trim();
                    if (trimmed.startsWith("{")) {
                        existingEntries.add(trimmed.endsWith(",")
                                ? trimmed.substring(0, trimmed.length() - 1)
                                : trimmed);
                    }
                }
            }
            existingEntries.add(entry);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write("[\n");
                for (int i = 0; i < existingEntries.size(); i++) {
                    writer.write("  " + existingEntries.get(i)
                            + (i < existingEntries.size() - 1 ? "," : ""));
                    writer.write("\n");
                }
                writer.write("]\n");
            }
            return "Message successfully stored.";
        } catch (IOException e) {
            return "Could not store message: " + e.getMessage();
        }
    }

    private String escapeJson(String text) {
        return text == null ? "" : text.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public int getMessageNumber() {
        return messageNumber;
    }
}