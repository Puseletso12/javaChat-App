/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javachatapp;

import java.util.regex.Pattern;

public class Login {
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
        if (phoneNumber == null) {
            return false;
        }
        
        String regex ="^\\+27[0-9]{9}$";
        return Pattern.matches(regex, phoneNumber);
    }
    
    public String registerUser (String username, String password, String firstName, String lastName, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character";
        }
        if (!checkCellPhoneNumber(phoneNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code";
        }
        
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "User registered successfully";
    }
    
    public boolean loginUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
        return "Welcome " + firstName + ", " + lastName + " it is great to see you again";
        } else {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length";
        }
    }
}
