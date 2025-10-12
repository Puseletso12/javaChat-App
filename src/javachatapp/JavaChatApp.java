/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javachatapp;

import java.util.Scanner;
/**
 *
 * @author pusel
 */
public class JavaChatApp {

    
    public static void main(String[] args) {
        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("== User Registration System ==");
        
        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();
        
        System.out.print("Enter your last name: ");
        String lastName = sc.nextLine();
        
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        System.out.print("Enter South African phone number (+27XXXXXXXXX or 0XXXXXXXXX)");
        String phoneNumber = sc.nextLine();
        
        String registrationResult = login.registerUser(username, password, firstName, lastName, phoneNumber);
        System.out.println(registrationResult);
        
        if (registrationResult.equals("User registered successfully")) {
            System.out.println("\n== Login System ==");
            
            System.out.print("Enter username: ");
            String loginUsername = sc.nextLine();
            
            System.out.print("Enter password: ");
            String loginPassword = sc.nextLine();
            
            String loginResult = login.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginResult);
        }
    }
}
 