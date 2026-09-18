package quickchat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageTest {


    @Test
    void testMessageIdIsNeverMoreThanTenCharacters() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(message.checkMessageID());
        assertTrue(message.getMessageID().length() <= 10);
    }


    @Test
    void testRecipientCellCorrectlyFormatted() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Recipient cell phone number successfully captured.", message.checkRecipientCell());
    }

    @Test
    void testRecipientCellIncorrectlyFormatted() {
        Message message = new Message("08575975889", "Don't forget to bring the dessert.");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code.",
                message.checkRecipientCell());
    }


    @Test
    void testMessageWithinLengthLimit() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message sent.", message.checkMessageLength());
    }

    @Test
    void testMessageExceedsLengthLimit() {
        String longText = "a".repeat(251);
        Message message = new Message("+27718693002", longText);
        assertEquals("Please enter a message of less than 250 characters.", message.checkMessageLength());
    }


    @Test
    void testCreateMessageHashFormat() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        message.sendMessage("send");

        String expectedPrefix = message.getMessageID().substring(0, 2);
        String expectedHash = expectedPrefix + ":" + message.getMessageNumber() + ":HITONIGHT?";
        assertEquals(expectedHash, message.getMessageHash());
    }


    @Test
    void testSendMessageReturnsSuccessAndIncrementsTotal() {
        int before = Message.returnTotalMessages();
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", message.sendMessage("send"));
        assertEquals(before + 1, Message.returnTotalMessages());
    }

    @Test
    void testDisregardMessage() {
        Message message = new Message("+27718693002", "Never mind, ignore this one.");
        assertEquals("Press 0 to delete the message.", message.sendMessage("disregard"));
    }

    @Test
    void testStoreMessage() {
        Message message = new Message("+27718693002", "Save this one for later.");
        assertEquals("Message successfully stored.", message.sendMessage("store"));
    }

    @Test
    void testInvalidSendOption() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Invalid option selected.", message.sendMessage("maybe"));
    }


    @Test
    void testPrintMessageDetailsContainsAllFields() {
        Message message = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?");
        message.sendMessage("send");
        String details = message.printMessageDetails();

        assertTrue(details.contains("Message ID: " + message.getMessageID()));
        assertTrue(details.contains("Message Hash: " + message.getMessageHash()));
        assertTrue(details.contains("Recipient: +27718693002"));
        assertTrue(details.contains("Message: Hi Mike, can you join us for dinner tonight?"));
    }
}

 
