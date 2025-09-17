/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javachatapp;

import javax.swing.JOptionPane;
/**
 *
 * @author pusel
 */
public class JavaChatApp {

    
    public static void main(String[] args) {
        Login login = new Login();
        String input = JOptionPane.showInputDialog(null, "Enter username: ", "Registration - Fullnames", JOptionPane.INFORMATION_MESSAGE);
        
       JOptionPane.showMessageDialog(null, "Welcome to the User Registration System", "Registration System", JOptionPane.INFORMATION_MESSAGE);
       
       String firstName = JOptionPane.showInputDialog(null, "Enter your first name: ", "Registration - First Name", JOptionPane.QUESTION_MESSAGE);
       String lastName = JOptionPane.showInputDialog(null, "Enter your last name: ", "Registration - Last Name", JOptionPane.QUESTION_MESSAGE);
       String userName = JOptionPane.showInputDialog(null, "Enter your Username: ", "Registration - Username", JOptionPane.QUESTION_MESSAGE);
       String password = JOptionPane.showInputDialog(null, "Enter your Password: ", "Registration - Password", JOptionPane.QUESTION_MESSAGE);
       String phoneNumber = JOptionPane.showInputDialog(null, "Enter your Phone Number: ", "Registration - Phone Number", JOptionPane.QUESTION_MESSAGE);
       
       if (firstName == null || lastName == null || userName == null || password == null || phoneNumber == null) {
           JOptionPane.showMessageDialog(null, "Registration cancelled by user.", "Registration Cancelled", JOptionPane.WARNING_MESSAGE);
           
           return;   
       }

       String registrationResult = login.registerUser(userName, firstName, lastName, password, phoneNumber);
       
       if (registrationResult.equals("User registered successfully")) {
           
           JOptionPane.showMessageDialog(null, registrationResult, "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
           JOptionPane.showMessageDialog(null, "Now you can log in to your account", "Login System", JOptionPane.INFORMATION_MESSAGE);
           
           String loginUsername = JOptionPane.showInputDialog(null, "Enter your username: ", "Login - Username", JOptionPane.QUESTION_MESSAGE);
           String loginPassword = JOptionPane.showInputDialog(null, "Enter your password: ", "Login - Password", JOptionPane.QUESTION_MESSAGE);
           
           if (loginUsername == null || loginPassword == null) {
               JOptionPane.showMessageDialog(null, "Login cancelled by user.", "Login Canelled", JOptionPane.WARNING_MESSAGE);
               return;
           }
           
           String loginResult = login.returnLoginStatus(loginUsername, loginPassword);
           if (login.loginUser(loginUsername, loginPassword)) {
               JOptionPane.showMessageDialog(null, loginResult, "Login Successful", JOptionPane.INFORMATION_MESSAGE);
           } else {
               JOptionPane.showMessageDialog(null, loginResult, "Login Failed", JOptionPane.ERROR_MESSAGE);
           } 
       }else {
                 JOptionPane.showMessageDialog(null, registrationResult, "Registration Failed", JOptionPane.ERROR_MESSAGE);
       }}
}
 