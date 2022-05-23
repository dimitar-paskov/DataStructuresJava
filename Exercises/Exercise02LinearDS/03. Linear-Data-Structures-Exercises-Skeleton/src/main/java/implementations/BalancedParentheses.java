package implementations;

import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
    private String parentheses;

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
    	
    	
    	
    	java.util.ArrayDeque<Character> stack = new java.util.ArrayDeque<Character>(); 
    	
    	boolean balanced = true;
    	
    	if(this.parentheses.isBlank()) {
    		return false;
    	}
    	
    	for (Character character : this.parentheses.toCharArray()) {
			 if(character.equals('{')  || character.equals('(') ||character.equals('[') ) {
				 stack.push(character);
			 }else if(character.equals('}')  ) {
				 
				 if(!(Character.compare(stack.pop(), '{') == 0)) {
					 balanced = false;
					 break;
				 }
				 
			 }else if(character.equals(']')) {
				 if(!(Character.compare(stack.pop(), '[') == 0)) {
					 balanced = false;
					 break;
				 }
				 
			 }else if(character.equals(')')) {
				 if(!(Character.compare(stack.pop(), '(') == 0)) {
					 balanced = false;
					 break;
				 }
			 }
			 
		}
    	
        return balanced;
    }
}
