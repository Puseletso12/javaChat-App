
package javachatapp;

import java.util.regex.Pattern;

/*
* LOGIN SYSTEM - USER REGISTRATION AND AUTHENTICATION 

* Regex attribution: The regular expression for validating South African phone numbers
* was generated with assistance from ChatGPT (OpenAI, 2025).
*
* APA reference:
* OpenAI. 2025. ChatGPT (February 2025 version). [online]
* Available at: https://chat.openai.com [Accessed 13 September 2025]
*/

public class Login {
    
    private static final String USERNAME_SUCCESS_MSG = "Username successfully captured.";
    private static final String USERNAME_ERROR_MSG = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length";
    private static final String PASSWORD_SUCCESS_MSG = "Password successfully captured.";
    private static final String PASSWORD_ERROR_MSG = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character";
    private static final String PHONE_SUCCESS_MSG = "Cell number successfully added.";
    private static final String PHONE_ERROR_MSG = "Cell phone number incorrectly formatted or does not contain international code";
    private static final String REGISTRATION_SUCCESS_MSG = "User registered successfully!";
    private static final String LOGIN_SUCCESS_FORMAT = "Welcome %s %s, it is great to see you again";
    private static final String LOGIN_ERROR_MSG = "Username or password incorrect, please try again";
    private static final String SA_PHONE_REGEX = "^(\\+27|0)[0-9]{9}$";
    
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;
    
    public Login(String username, String password, String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        
    }

    public Login() {}
    
    public boolean checkUserName (String username) {
       return username != null && username.contains("_") && username.length() <= 5;  
    }
    
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        
        for (char c: password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialCharacter = true;
            }
        }
        
        return hasCapital && hasNumber && hasSpecialCharacter;
    }
    
    public boolean checkCellPhoneNumber (String phoneNumber) {
        if (phoneNumber == null) return false; 
            return Pattern.matches(SA_PHONE_REGEX, phoneNumber);
    }
    
    public String registerUser (String username, String password, String firstName, String lastName, String phoneNumber) {
        if (!checkUserName(username)) return USERNAME_ERROR_MSG;
        if (!checkPasswordComplexity(password)) return PASSWORD_ERROR_MSG;
        if (!checkCellPhoneNumber(phoneNumber)) return PHONE_ERROR_MSG;
        
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        
        return REGISTRATION_SUCCESS_MSG;
    }
    
    public boolean loginUser(String username, String password) {
        if (username == null || password == null) return false;
        if (this.username == null || this.password == null) return false;
        return this.username.equals(username) && this.password.equals(password);
    }
    
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return String.format(LOGIN_SUCCESS_FORMAT, firstName, lastName);
        } else {
            return LOGIN_ERROR_MSG;
        }
    }
}
