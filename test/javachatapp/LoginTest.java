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
        assertFalse("The username does not contain an underscore and is no more than five characters long.", result);
    }
    
    @Test
    public void testPasswordMeetsComplexityRequirements() {
        String password = "Ch&&sec@ke99!";
        boolean result = login.checkPasswordComplexity(password);
        assertTrue ("Password should meet complexity requirements", result);
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        String password = "password";
        boolean result = login.checkPasswordComplexity(password);
        assertFalse ("Password should not meet complexity requirements", result);
    }
    
    @Test
    public void testPhoneNumberCorrectlyFormatted() {
        String phoneNumber = "+27838368976";
        boolean result = login.checkCellPhoneNumber(phoneNumber);
        assertTrue ("Phone number should be correctly formatted", result);
    }
}
