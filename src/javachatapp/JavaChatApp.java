package javachatapp;

import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 * QUICKCHAT APPLICATION - MAIN CLASS
 * PART 1 (LOGIN SYSTEM) AND PART 2 (MESSAGING SYSTEM)
 * @author pusel
 */
public class JavaChatApp {

    
    public static void main(String[] args) {
        Login login = new Login();
        Scanner sc = new Scanner(System.in);
        boolean isLoggedIn = false; 
        
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
        
        if (registrationResult.equals("User registered successfully!")) {
            System.out.println("\n== Login System ==");
            
            System.out.print("Enter username: ");
            String loginUsername = sc.nextLine();
            
            System.out.print("Enter password: ");
            String loginPassword = sc.nextLine();
            
            String loginResult = login.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginResult);
            
            isLoggedIn = login.loginUser(loginUsername, loginPassword);
        }
         
        if (isLoggedIn) {
            runQuickChatMenu (sc);
        } else {
            System.out.println("\nYou must be logged in to access QuickChat");
        }
        sc.close();
    }
    
    private static void runQuickChatMenu(Scanner scanner) {
        boolean running = true;
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("Welcome to QuickChat");
        System.out.println("=".repeat(40));
        
        while (running) {
            System.out.println("\nPlease select an option: ");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("Enter your choice: ");
            
            int choice = getIntInput(scanner);
            
            switch (choice) {
                case 1: 
                    sendMessages(scanner);
                    break;
                case 2: 
                    System.out.println("\nComing Soon");
                    break;
                case 3: 
                    System.out.println("\nThank you for using QuickChat. Goodbye!");
                    running = false; 
                    break;
                default: 
                    System.out.println("\nInvalid option. Please select 1, 2, or 3.");
            }
        }
    }
    
    private static void sendMessages(Scanner scanner) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("SEND MESSAGES");
        System.out.println("=".repeat(40));
        
        System.out.println("How many messages do you want to send? ");
        int numMessages = getIntInput(scanner);
        
        if (numMessages <= 0) {
            System.out.println("Invalid number of messages.");
            return;
        }
        
        Message.resetTotalMessages();
        
        Message[] messages = new Message[numMessages];
        int messagesSent = 0;
        
        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + "---");
            
            System.out.println("Enter recipient phone number (+27XXXXXXXXXX): ");
            String recipient = scanner.nextLine();
            
            System.out.println("Enter your message: ");
            String messageText = scanner.nextLine();
            
            Message message = new Message(i, recipient, messageText);
            
            String lengthCheck = message.checkMessageLength();
            System.out.println(lengthCheck);
            
            if (!lengthCheck.equals("Message ready to send")) {
                System.out.println("Message not sent due to length issue. ");
                continue;
            }
            
            int recipientCheck = message.checkRecipientCell();
            if (recipientCheck == 0) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                continue; 
            } else {
                System.out.println("Cell phone number successfully captured.");
            }
            
            System.out.println("\n" + message.printMessage());
            System.out.println("\nMessage Hash: " + message.getMessageHash());
            
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) Send message");
            System.out.println("2) Discard message");
            System.out.println("3) Store message to send later");
            System.out.println("Enter your choice");
            
            int sendChoice = getIntInput(scanner);
            String result = message.sendMessage(sendChoice);
            System.out.println(result);
            
            if (sendChoice == 3) {
                String jsonResult = message.storeMessageToJSON("messages.json");
                System.out.println(jsonResult);
            }
            
            messages[i] = message;
            
            if (sendChoice == 1) {
                messagesSent++;
                
                JOptionPane.showMessageDialog(null, 
                        message.printMessage(), 
                        "Message Sent",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        int totalSent = Message.returnTotalMessages();
        System.out.println("\n" + "=".repeat(40));
        System.out.println("Total messages sent: " + totalSent);
        System.out.println("=".repeat(40));
        
        JOptionPane.showMessageDialog(null, 
                "Total messages sent in the session: " + totalSent,
                "Session Summary",
                JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static int getIntInput(Scanner scanner) {
        while(true) {
            try {
            String input = scanner.nextLine();
            return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number: ");
            }
        }
    }
}
 