package implementations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BalancedParenthesesTest {
    @Test
    public void zeroTestOne() {
        String input = "{[()]}";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertTrue(solve);
    }

    @Test
    public void zeroTestTwo() {
        String input = "{[(])}";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertFalse(solve);
    }
    
    @Test
    public void zeroTestThree() {
        String input = "";
        Boolean solve = new BalancedParentheses(input).solve();
        assertNotNull(solve);
        assertFalse(solve);
    }
}