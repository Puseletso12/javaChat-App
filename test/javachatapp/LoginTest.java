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
        assertTrue("Username should be correctly formatted", result);
    }
    
    @Test 
    public void testUserNameIncorrectlyFormatted() {
        String username = "kyle!!!!!!!";
        boolean result = login.checkUserName(username);
        assertFalse("Username should be incorrectly formatted", result);
    }
    
}
