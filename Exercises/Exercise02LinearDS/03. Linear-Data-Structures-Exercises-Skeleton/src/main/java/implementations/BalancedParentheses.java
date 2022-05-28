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
    	
    	if(this.parentheses == null || this.parentheses.isBlank()) {
    		return false;
    	}
    	
    	for (Character character : this.parentheses.toCharArray()) {
			 if(character.equals('{')  || character.equals('(') ||character.equals('[') ) {
				 stack.push(character);
			 }else if(character.equals('}')  ) {
				 
				 if(stack.isEmpty() || stack.pop() != '{') {
					 balanced = false;
					 break;
				 }
				 
			 }else if(character.equals(']')) {
				 if(stack.isEmpty() || stack.pop() != '[') {
					 balanced = false;
					 break;
				 }
				 
			 }else if(character.equals(')')) {
				 if(stack.isEmpty() || stack.pop() != '(') {
					 balanced = false;
					 break;
				 }
			 }
			 
		}
    	
        return balanced;
    }
}
