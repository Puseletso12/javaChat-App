package javachatapp;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class LoginTest {
    private Login login;
    
    @Before 
    public void setUp() {
        login = new Login();
    }
    
    @Test
    public void testUserNameCorrectlyFormatted() {
        String username = "kyl_1";
        boolean result = login.checkUserName(username);
        assertTrue("The username contains an underscore and is no more than five characters long.", result);
    }
    
    @Test 
    public void testUserNameIncorrectlyFormatted() {
        String username = "kyle!!!!!!!";
        boolean result = login.checkUserName(username);
        assertFalse("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }
    
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        String password = "Ch&&sec@ke99!";
        boolean result = login.checkPasswordComplexity(password);
        assertTrue ("Password successfully captured.", result);
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        String password = "password";
        boolean result = login.checkPasswordComplexity(password);
        assertFalse ("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character", result);
    }
    
    @Test
    public void testPhoneNumberCorrectlyFormatted() {
        String phoneNumber = "+27838368976";
        boolean result = login.checkCellPhoneNumber(phoneNumber);
        assertTrue ("Cell number successfully captured", result);
    }
    
    @Test 
    public void testPhoneNumberIncorrectlyFormatted() {
        String phoneNumber = "08966553";
        boolean result = login.checkCellPhoneNumber(phoneNumber);
        assertFalse("Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again", result);
    }
}
