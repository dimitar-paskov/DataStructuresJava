package solutions;

import java.util.PriorityQueue;
import java.util.Queue;

public class CookiesProblem {
    public Integer solve(int requiredSweetness, int[] cookiesSweetness) {
    	
    	Queue<Integer> cookies = new PriorityQueue<>();
    	
    	for (Integer sweetness : cookiesSweetness) {
			cookies.offer(sweetness);
		}
    	
    	
    	int currentMinSweetnes = cookies.peek();
    	
    	int steps = 0;
    	while(currentMinSweetnes < requiredSweetness && cookies.size() > 1) {
    		
    		int leastSweetCookie = cookies.poll();
    		int secondLeastSweetCookie = cookies.poll();
    		
    		int combinedSweetness = leastSweetCookie + 2 * secondLeastSweetCookie;
    		
    		cookies.add(combinedSweetness);
    		
    		currentMinSweetnes = cookies.peek();
    		steps++;
    		
    	}
    	
    	
        return currentMinSweetnes > requiredSweetness ? steps : -1 ;
    }
}
