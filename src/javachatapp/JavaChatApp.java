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
        
        String message = login.registerUser(input);
        JOptionPane.showMessageDialog(null, message);
        
        String loginMessage = login.returnLoginStatus(input);
        JOptionPane.showMessageDialog(null, loginMessage);
        
        
    }
    
}
 