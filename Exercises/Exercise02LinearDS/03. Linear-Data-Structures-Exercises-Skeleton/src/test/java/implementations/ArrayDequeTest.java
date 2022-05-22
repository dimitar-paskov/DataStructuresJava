package implementations;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ArrayDequeTest {
	private ArrayDeque<String> queue;

//    @Before
//    public void setUp() {
//        this.queue = new ArrayDeque<>();
//        for (int i = 0; i < 100; i++) {
//            queue.offer(String.valueOf(i));
//            queue.offer(String.valueOf(i));
//        }
//    }

	@Before
	public void setUp() {
		this.queue = new ArrayDeque<>();
		int j = 50;
		for (int i = 0; i < 50; i++) {
			queue.addFirst(String.valueOf(j - i));
			queue.offer(String.valueOf(j + i));
		}
	}

	@Test
	public void testArrayDeque() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);
		deque.addFirst(10);
		deque.addFirst(9);
		deque.addFirst(8);

		deque.insert(7, 77);

		deque.remove(Integer.valueOf(13));

		for (Integer integer : deque) {
			System.out.println(integer);
		}

//        System.out.println();
//        
//        for (String integer : queue) {
//			System.out.println(integer);
//		}

//        System.out.println();
////        assertEquals("0", queue.peek());
	}

	@Test
	public void testPopArrayDeque() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);
		deque.addFirst(10);
		deque.addFirst(9);
		deque.addFirst(8);

		deque.insert(7, 77);

		assertEquals(Integer.valueOf(15),(Integer)deque.pop());

	}
	
	@Test
	public void testPollArrayDeque() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);
		deque.addFirst(10);
		deque.addFirst(9);
		deque.addFirst(8);

		deque.insert(7, 77);

		assertEquals(Integer.valueOf(8),(Integer)deque.poll());

	}
	
	@Test
	public void testGetObject() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);
		deque.addFirst(10);
		deque.addFirst(9);
		deque.addFirst(8);

		deque.insert(7, 77);

		assertEquals(Integer.valueOf(10),deque.get(Integer.valueOf("10")));

	}
	
	@Test
	public void testGetObjectReturnNull() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);
		deque.addFirst(10);
		deque.addFirst(9);
		deque.addFirst(8);

		deque.insert(7, 77);

		assertEquals(null,deque.get(Integer.valueOf("7")));

	}

}