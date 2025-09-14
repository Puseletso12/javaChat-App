/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javachatapp;

//import java.util.regex.Pattern;

public class Login {
    private String username;
    
    public Login(String username, String password) {
        this.username = username;
    }

    public Login() {}
    
    public boolean checkUserName (String username) {
       return username != null && username.contains("_") && username.length() <= 5;
        
    }
    
    public String registerUser (String username) {
        if (!checkUserName(username)) {
        return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length";
        }
        
        this.username = username;
        
        return "User registered successfully";
    }
    
    public boolean loginUser(String username) {
        return this.username.equals(username);
    }
    
    public String returnLoginStatus(String username) {
        if (loginUser(username)) {
        return "Welcome " + username;
        } else {
            return "Username or password is incorrect, please try again";
        }
    }
}
