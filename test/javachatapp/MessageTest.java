
package javachatapp;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 *
 * @author pusel
 */
public class MessageTest {
    
    private Message message1;
    private Message message2;
    
    @Before
    public void setUp() {
        Message.resetTotalMessages();
        
        message1 = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        message2 = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");
    }
    
    @After
    public void tearDown() {
        Message.resetTotalMessages();
    }

    
    @Test
    public void testMessageLength_Success_WithinLimit() {
        String result = message1.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }

    @Test 
    public void testMessageLength_Failure_ExceedsLimit() {
        String longMessage = new String (new char[260]).replace("\0", "a");
        Message longMsg = new Message(0, "+27718693002", longMessage);
        
        String result = longMsg.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 10, please reduce size.", result);
    }
    
    @Test
    public void testMessageLength_250Characters() {
        StringBuilder exactMessage = new StringBuilder();
        for (int i = 0; i < 250; i++) {
            exactMessage.append("a");
        }
        Message exactMsg = new Message(0, "+27718693002", exactMessage.toString());
        String result = exactMsg.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }
    @Test
    public void testMessageLength_251Characters() {
        StringBuilder overMessage = new StringBuilder();
        for (int i = 0; i < 251; i++) {
            overMessage.append("a");
        }
        Message overMsg = new Message(0, "+27718693002", overMessage.toString());
        String result = overMsg.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 1, please reduce size.", result);
    }
    
        //RECIPIENT VALIDATION TESTS
    @Test
    public void testRecipientCell_ValidNumber() {
        int result = message1.checkRecipientCell();
        assertEquals(1, result);
    }
    @Test
    public void testRecipientCell_NoInternationalCode() {
        int result = message2.checkRecipientCell();
        assertEquals(0, result);
    }
    
    @Test 
    public void testRecipientCell_WrongCountryCode() {
        Message msg = new Message(0, "+28718693002", "Hello");
        int result = msg.checkRecipientCell();
        assertEquals(0, result);
    }
    
    //MESSAGE HASH TESTS 
    
    @Test 
    public void testCreateMessageHash_FormatCheck() {
        String hash = message1.getMessageHash();
        assertTrue("Hash format should match pattern", hash.matches("\\d{2}:\\d+:[A-Z]+"));
    }
    @Test 
    public void testCreateMessageHash_ContainsFirstAndLastWord() {
        String hash = message1.getMessageHash();
        assertTrue("Should contain first word in uppercase", hash.contains("HI"));
        assertTrue("Should contain last word in uppercase", hash.contains("TONIGHT"));
    }
    
    @Test 
    public void testCreateMessageHash_SingleWord() {
        Message msg = new Message(0, "+27718693002", "Hello");
        String hash = msg.getMessageHash();
        assertTrue(hash.endsWith("HELLOHELLO"));
    }
    
    // MESSAGE ID TESTS
    
    @Test
    public void testMessageID_IsValid() {
        assertTrue(message1.checkMessageID());
        assertEquals(10, message1.getMessageID().length());
    }
    @Test
    public void testMessageID_IsNumeric() {
        assertTrue(message1.getMessageID().matches("\\d{10}"));
    }
    
    // SEND MESSAGE TESTS
    @Test 
    public void testSendMessage_SendOption() {
        String result = message1.sendMessage(1);
        assertEquals("Message successfully sent.", result);
    }
    @Test 
    public void testSendMessage_DiscardOption() {
        String result = message1.sendMessage(2);
        assertEquals("Press 0 to delete message.", result);
    }
    @Test 
    public void testSendMessage_StoreOption() {
        String result = message1.sendMessage(3);
        assertEquals("Message successfully stored.", result);
    }
    @Test 
    public void testSendMessage_InvalidOption() {
        String result = message1.sendMessage(9);
        assertEquals("Invalid choice", result);
    }
    
    // MESSAGE COUNT TESTS
    
    @Test 
    public void testTotalMessages_AfterSend() {
        assertEquals(0, Message.returnTotalMessages());
        message1.sendMessage(1);
        assertEquals(1, Message.returnTotalMessages());
    }
    @Test 
    public void testTotalMessages_DiscardNotCounted() {
        message1.sendMessage(1);
        message2.sendMessage(2);
        assertEquals(1, Message.returnTotalMessages());
    }
    @Test 
    public void testTotalMessages_Reset() {
        message1.sendMessage(1);
        message2.sendMessage(1);
        assertEquals(2, Message.returnTotalMessages());
        Message.resetTotalMessages();
        assertEquals(0, Message.returnTotalMessages());
    }
    
    // PRINT MESSAGE TEST 
    @Test
    public void testPrintMessage_ContainsAllFields() {
        String printed = message1.printMessage();
        assertTrue(printed.contains("Message ID: "));
        assertTrue(printed.contains("Message Hash: "));
        assertTrue(printed.contains("Recipient: "));
        assertTrue(printed.contains("Message: "));
    }
    
    // JSON STORAGE TESTS
    
    @Test 
    public void testStoreMessageToJSON_CreatesFile() {
        String filename = "test_messages.json";
        String result = message1.storeMessageToJSON(filename);
        assertTrue(result.contains("successfully"));
        java.io.File file = new java.io.File(filename);
        assertTrue(file.exists());
        file.delete();
    }
    
    @Test 
    public void testStoreMessageToJSON_HandlesSpecialCharacters() {
        Message msg = new Message(0, "+27718693002", "Hello \"John\"! How are you?");
        String filename = "test_special.json";
        String result = msg.storeMessageToJSON(filename);
        assertTrue(result.toLowerCase().contains("successfully"));
        new java.io.File(filename).delete();
    }
}
