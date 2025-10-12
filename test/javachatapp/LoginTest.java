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
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    @Test 
    public void testUserNameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }
    
    @Test
    public void testPasswordMeetsComplexityRequirements() {
//        String password = "Ch&&sec@ke99!";
//        boolean result = login.checkPasswordComplexity(password);
        assertTrue (login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
//        String password = "password";
//        boolean result = login.checkPasswordComplexity(password);
        assertFalse (login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void testPhoneNumberCorrectlyFormatted() {
//        String phoneNumber = "+27838368976";
//        boolean result = login.checkCellPhoneNumber(phoneNumber);
        assertTrue (login.checkCellPhoneNumber("+27838368976"));
    }
    
    @Test 
    public void testPhoneNumberIncorrectlyFormatted() {
//        String phoneNumber = "08966553";
//        boolean result = login.checkCellPhoneNumber(phoneNumber);
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
    
    @Test 
    public void testRegisterUser_Successful() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "Puseletso", "Chuene", "+27838368976");
        assertEquals("User registered successfully!", result);
    }
    
    @Test 
    public void testRegister_InvalidUsername() {
        String result = login.registerUser("kyle!!!!!", "Ch&&sec@ke99", "Puseletso", "Chuene", "+27838368976");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length", result);
    }
    
    @Test
    public void testRegister_InvalidPassword() {
        String result = login.registerUser("kyl_1", "password", "Puseletso", "Chuene", "+27838368976");
        assertEquals("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character", result);
    }
    
    @Test
    public void testRegister_InvalidPhone() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "Puseletso", "Chuene", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code", result);
    }
    
    @Test
    public void testLoginUser_CorrectCredentials() {
        login.registerUser("kyl_1", "Ch&&sec@ke99", "Puseletso", "Chuene", "+27838368976");
        assertTrue("Login should succeed with correct credentials", login.loginUser("kyl_1", "Ch&&sec@ke99"));
    }
    
    @Test
    public void testLoginUser_IncorrectCredentials() {
        login.registerUser("kyl_1", "Ch&&sec@ke99", "Puseletso", "Chuene", "+27838368976");
        assertFalse("Login should fail with incorrect credentials", login.loginUser("kyl_1", "wrongPass"));
    }
    
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "Puseletso", "Chuene", "+27838368976");
        String status = login.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Puseletso Chuene, it is great to see you again", status);
    }
    
    @Test
    public void testReturnLoginStatus_Failure() {
        login.registerUser("kyl_1", "Ch&&sec@ke99", "Puseletso", "Chuene", "+27838368976");
        String status = login.returnLoginStatus("kyl_1", "wrongPass");
        assertEquals("Username or password incorrect, please try again", status);
    }
}
