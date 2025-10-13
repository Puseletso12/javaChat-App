/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javachatapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pusel
 */
public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        Message instance = null;
        boolean expResult = false;
        boolean result = instance.checkMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     */
    @Test
    public void testCheckRecipientCell() {
        System.out.println("checkRecipientCell");
        Message instance = null;
        int expResult = 0;
        int result = instance.checkRecipientCell();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkMessageLength method, of class Message.
     */
    @Test
    public void testCheckMessageLength() {
        System.out.println("checkMessageLength");
        Message instance = null;
        String expResult = "";
        String result = instance.checkMessageLength();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMessageHash method, of class Message.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Message instance = null;
        String expResult = "";
        String result = instance.createMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class Message.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        int choice = 0;
        Message instance = null;
        String expResult = "";
        String result = instance.sendMessage(choice);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printMessage method, of class Message.
     */
    @Test
    public void testPrintMessage() {
        System.out.println("printMessage");
        Message instance = null;
        String expResult = "";
        String result = instance.printMessage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        int expResult = 0;
        int result = Message.returnTotalMessages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetTotalMessages method, of class Message.
     */
    @Test
    public void testResetTotalMessages() {
        System.out.println("resetTotalMessages");
        Message.resetTotalMessages();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of storeMessageToJSON method, of class Message.
     */
    @Test
    public void testStoreMessageToJSON() {
        System.out.println("storeMessageToJSON");
        String filename = "";
        Message instance = null;
        String expResult = "";
        String result = instance.storeMessageToJSON(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageID method, of class Message.
     */
    @Test
    public void testGetMessageID() {
        System.out.println("getMessageID");
        Message instance = null;
        String expResult = "";
        String result = instance.getMessageID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageNumber method, of class Message.
     */
    @Test
    public void testGetMessageNumber() {
        System.out.println("getMessageNumber");
        Message instance = null;
        int expResult = 0;
        int result = instance.getMessageNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecipient method, of class Message.
     */
    @Test
    public void testGetRecipient() {
        System.out.println("getRecipient");
        Message instance = null;
        String expResult = "";
        String result = instance.getRecipient();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageText method, of class Message.
     */
    @Test
    public void testGetMessageText() {
        System.out.println("getMessageText");
        Message instance = null;
        String expResult = "";
        String result = instance.getMessageText();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMessageHash method, of class Message.
     */
    @Test
    public void testGetMessageHash() {
        System.out.println("getMessageHash");
        Message instance = null;
        String expResult = "";
        String result = instance.getMessageHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
