package com.prog5121;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 Unit Tests for Part 2 - Message Class
 */
public class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        Message.resetTotalMessages();
        message = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", "00:0:HITONIGHT", "Pending");
    }

    @Test
    public void testMessageLengthSuccess() {
        String result = message.validateMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMsg.append("a");
        }
        String result = message.validateMessageLength(longMsg.toString());
        assertTrue(result.contains("Message exceeds 250 characters"));
    }

    @Test
    public void testRecipientCellCorrect() {
        assertTrue(message.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testRecipientCellIncorrect() {
        assertFalse(message.checkRecipientCell("08575075889"));
    }

    @Test
    public void testCreateMessageHash() {
        String hash = message.createMessageHash("0012345678", 0, "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", hash);
    }

    @Test
    public void testSentMessageOptionSend() {
        assertEquals("Message successfully sent", message.sentMessage(1));
        assertEquals("Sent", message.getFlag());
    }

    @Test
    public void testSentMessageOptionDisregard() {
        assertEquals("Press 0 to delete the message", message.sentMessage(2));
        assertEquals("Disregarded", message.getFlag());
    }

    @Test
    public void testSentMessageOptionStore() {
        assertEquals("Message successfully stored", message.sentMessage(3));
        assertEquals("Stored", message.getFlag());
    }
}
