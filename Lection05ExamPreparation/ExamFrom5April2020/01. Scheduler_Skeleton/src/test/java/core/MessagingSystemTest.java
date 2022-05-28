package core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Message;
import model.TextMessage;
import shared.DataTransferSystem;

public class MessagingSystemTest {
    private List<Message> messages;

    private DataTransferSystem system;

    @Before
    public void setUp() {
        this.messages = List.of(
                new TextMessage(11, "test_text"),
                new TextMessage(6, "test_text"),
                new TextMessage(19, "test_text"),
                new TextMessage(4, "test_text"),
                new TextMessage(8, "test_text"),
                new TextMessage(17, "test_text")
        );

        this.system = new MessagingSystem();

        assertEquals(0, this.system.size());

        for (Message message : this.messages) {
            this.system.add(message);
        }
        assertEquals(6, this.system.size());
    }

    @Test
    public void testAddSingleShouldWorkCorrectly() {
        DataTransferSystem system = new MessagingSystem();

        assertEquals(0, system.size());

        system.add(new TextMessage(12, "test_text"));

        assertEquals(1, system.size());
    }

    @Test
    public void testAddMultipleShouldWorkCorrectly() {
        DataTransferSystem system = new MessagingSystem();

        assertEquals(0, system.size());

        for (Message message : messages) {
            system.add(message);
        }

        assertEquals(messages.size(), system.size());
    }

    @Test
    public void testAddMultipleShouldSetCorrectElements() {
        assertEquals(messages.size(), system.size());
        Message lightest = system.getLightest();
        assertNotNull(lightest);
        assertEquals(4, lightest.getWeight());
        Message heaviest = system.getHeaviest();
        assertNotNull(heaviest);
        assertEquals(19, heaviest.getWeight());
    }

    @Test
    public void testGetPostOrderShouldReturnCorrectSequence() {
        List<Message> postOrder = this.system.getPostOrder();

        int[] expected = {4, 8, 6, 17, 19, 11};
        assertNotNull(postOrder);
        assertEquals(expected.length, postOrder.size());

        for (int i = 0; i < messages.size(); i++) {
            assertEquals(expected[i], postOrder.get(i).getWeight());
        }
    }
    
    @Test
    public void testGetPreOrderShouldReturnCorrectSequence() {
        List<Message> postOrder = this.system.getPreOrder();

        int[] expected = {11, 6, 4, 8, 19,17};
        assertNotNull(postOrder);
        assertEquals(expected.length, postOrder.size());

        for (int i = 0; i < messages.size(); i++) {
            assertEquals(expected[i], postOrder.get(i).getWeight());
        }
    }
    
    @Test
    public void testGetInOrderShouldReturnCorrectSequence() {
        List<Message> postOrder = this.system.getInOrder();

        int[] expected = {4,6,8,11,17,19};
        assertNotNull(postOrder);
        assertEquals(expected.length, postOrder.size());

        for (int i = 0; i < messages.size(); i++) {
            assertEquals(expected[i], postOrder.get(i).getWeight());
        }
    }
    
    

    @Test
    public void testGetLightestShouldReturnCorrect() {
        Message lightest = this.system.getLightest();
        assertNotNull(lightest);
        assertEquals(4, lightest.getWeight());
    }

    @Test
    public void testGetHeaviestShouldReturnCorrect() {
        Message heaviest = this.system.getHeaviest();
        assertNotNull(heaviest);
        assertEquals(19, heaviest.getWeight());
    }
    
    @Test
    public void testDeleteLightestShouldReturnCorrect() {
        Message lightest = this.system.deleteLightest();
        assertNotNull(lightest);
        assertEquals(4, lightest.getWeight());
        assertEquals(5, this.system.size());
    }
    
    @Test
    public void testDeleteHeaviestShouldReturnCorrect() {
        Message heaviest = this.system.deleteHeaviest();
        assertNotNull(heaviest);
        assertEquals(19, heaviest.getWeight());
        assertEquals(5, this.system.size());
    }
    
    @Test
    public void testContainsTrue() {
        assertTrue(this.system.contains(new TextMessage(11, "test_text")));
        
    }
    
    @Test
    public void testContainsFalse() {
        assertFalse(this.system.contains(new TextMessage(10, "test_text")));
        
    }
}