/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
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

    /**
     * Test of checkMessageID method, of class Message.
     */
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
}
