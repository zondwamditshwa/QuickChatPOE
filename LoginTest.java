package quickchat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoginTest {


    @Test
    void testUsernameCorrectlyFormatted() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        assertTrue(login.checkUserName());
    }

    @Test
    void testUsernameIncorrectlyFormatted() {
        // No underscore, and more than five characters.
        Login login = new Login("Kyle", "Sithole", "kylesithole", "Ch&@sec@ke99!", "+27838968976");
        assertFalse(login.checkUserName());
    }


    @Test
    void testPasswordMeetsComplexityRequirements() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    void testPasswordDoesNotMeetComplexityRequirements() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "password", "+27838968976");
        assertFalse(login.checkPasswordComplexity());
    }


    @Test
    void testCellPhoneNumberCorrectlyFormatted() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    void testCellPhoneNumberIncorrectlyFormatted() {
        // Missing the international code.
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "08966553");
        assertFalse(login.checkCellPhoneNumber());
    }


    @Test
    void testRegisterUserSuccessMessage() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        String result = login.registerUser();
        assertTrue(result.contains("Username successfully captured."));
        assertTrue(result.contains("Password successfully captured."));
        assertTrue(result.contains("Cell phone number successfully added."));
        assertTrue(result.contains("User registered successfully."));
    }

    @Test
    void testRegisterUserPasswordFailureMessage() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "password", "+27838968976");
        String expected = "Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, "
                + "and a special character.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    void testRegisterUserUsernameFailureMessage() {
        Login login = new Login("Kyle", "Sithole", "kylesithole", "Ch&@sec@ke99!", "+27838968976");
        String expected = "Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.";
        assertEquals(expected, login.registerUser());
    }

    @Test
    void testRegisterUserCellFailureMessage() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "08966553");
        String expected = "Cell phone number incorrectly formatted or does not contain international code.";
        assertEquals(expected, login.registerUser());
    }


    @Test
    void testLoginSuccessful() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&@sec@ke99!"));
    }

    @Test
    void testLoginFailed() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongPassword1!"));
    }

    @Test
    void testReturnLoginStatusSuccessMessage() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        assertEquals("Welcome Kyle, Sithole it is great to see you again.",
                login.returnLoginStatus(true));
    }

    @Test
    void testReturnLoginStatusFailureMessage() {
        Login login = new Login("Kyle", "Sithole", "kyl_1", "Ch&@sec@ke99!", "+27838968976");
        assertEquals("Username or password incorrect, please try again.",
                login.returnLoginStatus(false));
    }
}

 
