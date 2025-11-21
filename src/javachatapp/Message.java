
package javachatapp;

import java.util.Random;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * MESSAGE CLASS - QUICKCHAT MESSAGING SYSTEM 
 * 
 * This class handles the creation of messages, validation and management for the QuickChat Application.
 * 
 * AI tool attribution: 
 * The JSON storage method was created with assistance from ChatGPT (OpenAI)
 * APA Citation: OpenAI. (2025). ChatGPT [Large Language Model]. Available at: https://chat.openai.com
 */
public class Message {
    private static final int MAX_MESSAGE_LENGTH = 250;
    private static final int MAX_PHONE_LENGTH = 10;
    private static final int MESSAGE_ID_LENGTH = 10;
    
    private static final String MESSAGE_SUCCESS = "Message ready to send.";
    private static final String MESSAGE_TOO_LONG = "Message exceeds 250 characters by %d, please reduce size.";
    private static final String PHONE_SUCCESS = "Cell phone number successfully captured.";
    private static final String PHONE_ERROR = "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    private static final String MESSAGE_SENT = "Message successfully sent.";
    private static final String MESSAGE_DISCARD = "Press 0 to delete message.";
    private static final String MESSAGE_STORED = "Message successfully stored.";
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash; 
    private static int totalMessagesSent = 0;
    
    private static final String[] sentMessages = new String[100];
    private static final String[] disregardedMessages = new String[100];
    private static final String[] storedMessages = new String[100];
    private static final String[] messageIDs = new String [100];
    private static final String[] recipients = new String[100];
    private static final String[] messageHashes = new String[100];
    
    private static final String[] storedRecipients = new String [100];
    private static final String[] storedHashes = new String[100];
    
    private static int sentCount = 0;
    private static int disregardedCount = 0;
    private static int storedCount = 0;
    
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient; 
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }
    
    private String generateMessageID() {
        Random random = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < MESSAGE_ID_LENGTH; i++) {
            id.append(random.nextInt(10));
        }
        return id.toString();
    }
    
    public boolean checkMessageID() {
        return messageID != null && messageID.length() == MESSAGE_ID_LENGTH;
    }
    
    public int checkRecipientCell() {
        if (recipient == null) return 0;
        if (recipient.matches("^(\\+27|0)[0-9]{9}$")) return 1;
        return 0;    
    }
     
    public String checkMessageLength() {
        if (messageText == null) {
            return String.format(MESSAGE_TOO_LONG, 250);
        }
        int length = messageText.length();
        
        if (length <= MAX_MESSAGE_LENGTH) {
            return MESSAGE_SUCCESS;
        } else {
            int tooLong = length - MAX_MESSAGE_LENGTH;
            return String.format(MESSAGE_TOO_LONG, tooLong);
        }
    }
    
    public String createMessageHash() {
        String idPrefix = messageID.substring(0, 2);
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        return String.format("%s:%d:%s%s", idPrefix, messageNumber, firstWord.toUpperCase(), lastWord.toUpperCase());
    }
    
    public String sendMessage(int choice) {
        switch (choice) {
            case 1: 
                totalMessagesSent++;
                sentMessages[sentCount] = messageText;
                messageIDs[sentCount] = messageID;
                recipients[sentCount] = recipient; 
                messageHashes[sentCount] = messageHash;
                sentCount++;
                return MESSAGE_SENT;
            case 2: 
                disregardedMessages[disregardedCount++] = messageText;
                return MESSAGE_DISCARD;
            case 3: 
                storedMessages[storedCount] = messageText;
                storedRecipients[storedCount] = recipient;
                storedHashes[storedCount] = messageHash;
                storedCount++;
                return MESSAGE_STORED;
            default: return "Invalid choice";
        }
    }
    
    public String printMessage() {
        return String.format("Message ID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s", messageID, messageHash, recipient, messageText);
    }
    
    public static int returnTotalMessages() {
        return totalMessagesSent; 
    }
    
    public static void resetTotalMessages() {
        totalMessagesSent = 0;
        
        for (int i = 0; i < sentCount; i++) {
            sentMessages[i] = null;
            messageIDs[i] = null;
            recipients[i] = null;
            messageHashes[i] = null;
        }
        for (int i = 0; i < disregardedCount; i++) {
            disregardedMessages[i] = null;
        }
        for (int i = 0; i < storedCount; i++) {
            storedMessages[i] = null;
            storedRecipients[i] = null;
            storedHashes[i] = null;
        }
        sentCount = 0;
        disregardedCount = 0;
        storedCount = 0;
    }
    
    public static String displaySenderAndRecipient() {
        if (sentCount == 0) return "No sent messages to display.";
        
        StringBuilder sb = new StringBuilder("=== Sent Messages ===\n");
        for (int i = 0; i < sentCount; i++) {
            sb.append("Message ").append(i + 1).append(":\n")
              .append("Recipient: ").append(recipients[i]).append("\n")
              .append("Message: ").append(sentMessages[i]).append("\n\n");
        }
        return sb.toString();
    }
    
    public static String displayLongestMessage() {
        if (sentCount == 0) return "No sent messages to display.";
        String longest = sentMessages[0];
        
        for (int i = 1; i < sentCount; i++) {
            if (sentMessages[i].length() > longest.length()) {
                longest = sentMessages[i];
            }
        }
        return longest;
    }
    
    public static String searchMessageByRecipient(String search) {
        for (int i = 0; i < sentCount; i++) {
            if (recipients[i].contains(search)) {
                return sentMessages[i];
            }
        }
        return "No messages found for recipient: " + search;
    }
    
    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < sentCount; i++) {
            if (messageHashes[i].equals(hash)) {
                String deleted = sentMessages[i];
                
                for (int j = i; j < sentCount - 1; j++) {
                    sentMessages[j] = sentMessages[j + 1];
                    messageIDs[j] = messageIDs [j + 1];
                    recipients[j] = recipients[j + 1];
                    messageHashes[j] = messageHashes[j + 1];
                }
                sentCount--;
                
                return "Message \"" + deleted + "\" successfully deleted.";
            }
        }
        return "Message with hash " + hash + " not found.";
    }
    
    public static String displayMessageReport() {
        if (sentCount == 0) return "No sent messages to display.";
        
        StringBuilder sb = new StringBuilder("==== MESSAGE REPORT ====");
        
        for (int i = 0; i < sentCount; i++) {
            sb.append("Message ").append(i + 1).append("\n")
              .append("Hash: ").append(messageHashes[i]).append("\n")
              .append("Recipient: ").append(recipients[i]).append("\n")
              .append("Message: ").append(sentMessages[i]).append("\n\n");
        }
        sb.append("Total Sent Messages: ").append(sentCount);
        return sb.toString();
    }
    
    public static String[] searchAllMessagesByRecipient(String search) {
        java.util.List<String> results = new java.util.ArrayList<>();
        for (int i = 0; i < sentCount; i++) {
            if (recipients[i].contains(search)) {
                results.add(sentMessages[i]);
            }
        }
        
        return results.toArray(new String[0]);
    }
    
    /**
     * this stores messages to JSON file
     * 
     * AI Tool Attribution: This method was created with help from ChatGPT (OpenAI)
     * to handle what happens in the JSON file for messages.
     * 
     * APA Citation: OpenAI. (2025). ChatGPT. Available at: https://chat.openai.com
     * 
     * this method appends the message to a JSON array in the specified file.
     * if the file does not exist, it will create a new one with a JSON array.
     */
    
    public String storeMessageToJSON (String filename) {
    try {
        String jsonObject = String.format("{\n" + "\"messageID\": \"%s\", \n"
        + "\"messageNumber\": %d, \n" + "\"recipient\": \"%s\",\n" + "\"messageText\": \"%s\", \n" +
                "\"messageHash\": \"%s\", \n" + "\"timestamp\": \"%s\"\n" + "}",
                messageID, messageNumber, recipient, messageText.replace("\"", "\\\""),
                messageHash, java.time.LocalDateTime.now().toString()
        );
        
        java.nio.file.Path filePath = java.nio.file.Paths.get(filename);
        java.util.List<String> existingContent = new java.util.ArrayList<>();
        
        if (java.nio.file.Files.exists(filePath)) {
            existingContent = java.nio.file.Files.readAllLines(filePath);
        }
        
        java.util.List<String> newContent = new java.util.ArrayList<>();
        
        if (existingContent.isEmpty()) {
            newContent.add("[");
            newContent.add(jsonObject);
            newContent.add("]");
        } else {
            for (int i = 0; i < existingContent.size() - 1; i++) {
                newContent.add(existingContent.get(i));
            }
            String lastLine = existingContent.get(existingContent.size() - 1);
            if (lastLine.trim().equals("]")) {
                newContent.add("},");
                newContent.add(jsonObject);
                newContent.add("]");
            } else {
                newContent.add(lastLine + ",");
                newContent.add(jsonObject);
                newContent.add("]");
            }
        }
        
        java.nio.file.Files.write(filePath, newContent);
        
        return "Message successfully stored to " + filename;
        } catch (Exception e) {
            return "Error storing message: " + e.getMessage();
        }
    }
    
        public String getMessageID() {
            return messageID;
        }
        
        public int getMessageNumber() {
            return messageNumber;
        }
        
        public String getRecipient() {
            return recipient;
        }
        
        public String getMessageText() {
            return messageText;
        }
        
        public String getMessageHash() {
            return messageHash;
        }
        
        public static String[] getSentMessagesArray() {
            return sentMessages;
        }
        public static String[] getDisregardedMessagesArray() {
            return disregardedMessages;
        }
        public static String[] getStoredMessagesArray() {
            return storedMessages;
        }
    }