package implementations;

import static org.junit.Assert.assertEquals;

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

//	@Before
//	public void setUp() {
//		this.queue = new ArrayDeque<>();
//		int j = 50;
//		for (int i = 0; i < 50; i++) {
//			queue.addFirst(String.valueOf(j - i));
//			queue.offer(String.valueOf(j + i));
//		}
//	}

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


//		for (Integer integer : deque) {
//			System.out.println(integer);
//		}

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
		assertEquals(8, deque.size());

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
		assertEquals(Integer.valueOf(9),(Integer)deque.poll());
		assertEquals(7, deque.size());

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
		assertEquals(9, deque.size());

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
		assertEquals(9, deque.size());

	}
	
	@Test
	public void testGetAtIndex() {
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

		assertEquals(Integer.valueOf(10),deque.get(2));
		assertEquals(9, deque.size());

	}
	
	@Test
	public void testInsertIndexLessThanMiddleOddCount() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);				

		deque.insert(2, 77);
//		
      System.out.println("testInsert");
      
      for (Integer integer : deque) {
			System.out.println(integer);
		}

		
		assertEquals(Integer.valueOf(77),deque.get(2));
		assertEquals(6, deque.size());

	}
	
	@Test
	public void testInsertIndexLessThanMiddleEvenCount() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);		
		deque.offer(16);		

		deque.insert(2, 77);
//		
      System.out.println("testInsert");
      
      for (Integer integer : deque) {
			System.out.println(integer);
		}

		
		assertEquals(Integer.valueOf(77),deque.get(2));
		assertEquals(7, deque.size());

	}
	
	@Test
	public void testInsertIndexMoreThanMiddleOddCount() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(31);
		deque.offer(32);
		deque.offer(33);
		deque.offer(34);
		deque.offer(35);		
		deque.offer(36);		

		deque.insert(4, 77);
//		
      System.out.println("testInsert");
      
      for (Integer integer : deque) {
			System.out.println(integer);
		}

		
		assertEquals(Integer.valueOf(77),deque.get(4));
		assertEquals(7, deque.size());

	}
	
	@Test
	public void testInsertIndexMoreThanMiddleEvenCount() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(41);
		deque.offer(42);
		deque.offer(43);
		deque.offer(44);
		deque.offer(45);		
		deque.offer(46);		
		deque.offer(47);		

		deque.insert(4, 107);
//		
      System.out.println("testInsert");
      
      for (Integer integer : deque) {
			System.out.println(integer);
		}

		
		assertEquals(Integer.valueOf(107),deque.get(4));
		assertEquals(8, deque.size());

	}
	
	@Test
	public void testSet() {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(11);
		deque.offer(12);
		deque.offer(13);
		deque.offer(14);
		deque.offer(15);		

		deque.set(2, 77);
		
//      System.out.println("testSet");
//      
//      for (Integer integer : deque) {
//			System.out.println(integer);
//		}

		assertEquals(Integer.valueOf(77),deque.get(2));
		assertEquals(5, deque.size());
		assertEquals(7, deque.capacity());

	}

}