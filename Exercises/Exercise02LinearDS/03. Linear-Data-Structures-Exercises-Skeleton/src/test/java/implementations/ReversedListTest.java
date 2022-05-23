package implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ReversedListTest {
    private ReversedList<String> list;

    @Before
    public void setUp() {
        try {
            this.list = new ReversedList<>();
            for (int i = 0; i < 100; i++) {
                list.add(String.valueOf(i));
            }
        } catch (Exception ignored) {
            this.list = new ReversedList<>();
        }
    }

    @Test
    public void testAddSingleElementInFront() {
    	ReversedList<Integer> integers = new ReversedList<>();
        integers.add(73);
        assertEquals(Integer.valueOf(73), integers.get(0));
    }

   

    @Test
    public void testRemoveAtShouldRemoveElement() {
    	
//    	for (String string : list) {
//			System.out.println(string );
//		}
    	
        assertEquals("99", list.get(0));
        assertEquals("99", list.removeAt(0));
        assertEquals("98", list.get(0));
        assertEquals(99, list.size());
    }


    @Test
    public void testSize() {
        assertEquals(100, list.size());
        assertEquals(0, new ReversedList<>().size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        assertTrue(new ReversedList<>().isEmpty());
    }
   
}