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
        
        //Login possible only afater registration is successful
        
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
            System.out.println("3) Display message report");
            System.out.println("4) Search messages");
            System.out.println("5) Delete Message");
            System.out.println("6) Quit");
            System.out.println("Enter your choice: ");
            
            int choice = getIntInput(scanner);
            
            switch (choice) {
                case 1: 
                    sendMessages(scanner);
                    break;
                case 2: 
                    System.out.println(Message.displaySenderAndRecipient());
                    break;
                case 3: 
                    System.out.println(Message.displayMessageReport());
                    break;
                case 4: 
                    searchMessages(scanner);
                    break;
                case 5: 
                    deleteMessages(scanner);
                    break;
                case 6: 
                    running = false;
                    System.out.println("\nThank you for using QuickChat. Goodbye!");
                    break;
                default: 
                    System.out.println("\nInvalid option. Please select 1-6");
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
        
        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + "---");
            
            System.out.println("Enter recipient phone number (+27XXXXXXXXXX)");
            String recipient = scanner.nextLine();
            
            System.out.println("Enter your message: ");
            String messageText = scanner.nextLine();
            
            Message message = new Message(i, recipient, messageText);
            
            String lengthCheck = message.checkMessageLength();
            System.out.println(lengthCheck);
            
            if (!lengthCheck.equals("Message ready to send")) {
                System.out.println("Message not sent due to length issue.");
                continue;
            }
            
            if (message.checkRecipientCell() == 0) {
                System.out.println("Invalid phone number format.");
                continue;
            }
            
            System.out.println("\n" + message.printMessage());
            System.out.println("Message Hash: " + message.getMessageHash());
            
            System.out.println("\nWhat would you like to do?");
            System.out.println("1) Send message");
            System.out.println("2) Discard message");
            System.out.println("3) Store message to send later");
            System.out.println("Enter your choice");
            
            int sendChoice = getIntInput(scanner);
            System.out.println(message.sendMessage(sendChoice));
            
            if (sendChoice == 3) {
                message.storeMessageToJSON("messages.json");
            }
            
            if (sendChoice == 1) {
                JOptionPane.showMessageDialog(null, message.printMessage(),
                        "Message Sent",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
            System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
        }
               
    
    private static void searchMessages(Scanner scanner) {
        System.out.println("\n=== SEARCH MESSAGES ===");
        System.out.println("1) Search by recipient");
        System.out.println("2) Display longest message");
        System.out.println("3) Display all senders & recipients");
        System.out.println("Choice: ");
        
        int choice = getIntInput(scanner);
        
        switch (choice) {
            case 1: 
                System.out.println("Enter recipient: ");
                String searchRecipient = scanner.nextLine();
                String result = Message.searchMessageByRecipient(searchRecipient);
                System.out.println(result);
                break;
            
            case 2:
                System.out.println("\nLongest message: ");
                System.out.println(Message.displayLongestMessage());
                break;
            case 3:
                System.out.println(Message.displaySenderAndRecipient());
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void deleteMessages(Scanner scanner) {
        System.out.println("\n=== DELETE MESSAGE ===");
        System.out.println("Enter message hash: ");
        String hash = scanner.nextLine();
        
        System.out.println(Message.deleteMessageByHash(hash));
    }
    
    private static int getIntInput(Scanner scanner) {
        while(true) {
            try {
            return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number: ");
            }
        }
    }
    
}
 