package implementations;

import org.junit.Test;

public class ArrayDequeTest {
    private ArrayDeque<String> queue;

//    @Before
//    public void setUp() {
//        this.queue = new ArrayDeque<>();
//        for (int i = 0; i < 100; i++) {
//            queue.offer(String.valueOf(i));
//        }
//    }

    @Test
    public void testArrayDeque() {
    	ArrayDeque<Integer> deque = new ArrayDeque<>();
//        deque.offer(11);
//        deque.offer(12);
//        deque.offer(13);
//        deque.offer(14);
//        deque.offer(15);
        
        deque.remove(Integer.valueOf(15));

        
        for (Integer integer : deque) {
			System.out.println(integer);
		}
        System.out.println();
//        assertEquals("0", queue.peek());
    }

    
    
}