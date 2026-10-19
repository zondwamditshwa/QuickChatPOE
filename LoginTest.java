package com.prog5121;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 Unit Tests for Part 1 - Login Class
 */
public class LoginTest {

    private Login login;

    @BeforeEach
    public void setUp() {
        login = new Login("kyl_1", "Ch&&sec@ke99!", "+2783958976", "Kylie", "Smith");
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"), "Username containing underscore and <= 5 chars should be valid.");
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kylie!!!!!!!"), "Username exceeding 5 chars should be invalid.");
    }

    @Test
    public void testPasswordComplexitySuccess() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"), "Password meeting all complexity rules should be valid.");
    }

    @Test
    public void testPasswordComplexityFailure() {
        assertFalse(login.checkPasswordComplexity("password"), "Password lacking digits/special chars should be invalid.");
    }

    @Test
    public void testCellPhoneNumberCorrect() {
        assertTrue(login.checkCellPhoneNumber("+2783958976"), "Cell number with +27 code should be valid.");
    }

    @Test
    public void testCellPhoneNumberIncorrect() {
        assertFalse(login.checkCellPhoneNumber("08956553"), "Cell number without international code should be invalid.");
    }

    @Test
    public void testLoginSuccessful() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"), "Valid login credentials should return true.");
    }

    @Test
    public void testLoginFailed() {
        assertFalse(login.loginUser("kyl_1", "wrongpass"), "Invalid login credentials should return false.");
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        String expected = "Welcome Kylie Smith it is great to see you.";
        assertEquals(expected, login.returnLoginStatus(true));
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, login.returnLoginStatus(false));
    }
}
