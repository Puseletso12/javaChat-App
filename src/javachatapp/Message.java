
package javachatapp;

import java.util.Random;
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
    
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient; 
        this.messageText = messageText;
//        this.messageHash = generateMessageHash();
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
            case 1: totalMessagesSent++;
                return MESSAGE_SENT;
            case 2: return MESSAGE_DISCARD;
            case 3: return MESSAGE_STORED;
            default: return "Invalid choice";
        }
    }
    
    public String printMessage() {
        return String.format("Message ID: %\nMessage Hash: %s\nRecipient: %s\nMessage: %s", messageID, messageHash, recipient, messageText);
    }
    
    public static int returnTotalMessages() {
        return totalMessagesSent; 
    }
    
    public static void resetTotalMessages() {
        totalMessagesSent = 0;
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
    }