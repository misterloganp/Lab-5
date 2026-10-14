
package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * Tests the PrefixEvaluator class.
 */
public class PrefixEvaluatorTest {

    private int evaluate(String... tokens) {
        ArrayList<Token> expression = new ArrayList<>();

        for (String token : tokens) {
            if (token.matches("[0-9]+")) {
                expression.add(new NumToken(token));
            } else {
                expression.add(new OpToken(token.charAt(0)));
            }
        }

        expression.add(new OpToken(';'));
        return PrefixEvaluator.evaluate(expression);
    }

    @Test
    public void testAddition() {
        assertEquals(5, evaluate("+", "2", "3"));
    }

    @Test
    public void testSubtraction() {
        assertEquals(6, evaluate("-", "10", "4"));
    }

    @Test
    public void testMultiplication() {
        assertEquals(12, evaluate("*", "3", "4"));
    }

    @Test
    public void testDivision() {
        assertEquals(4, evaluate("/", "12", "3"));
    }

    @Test
    public void testNestedExpression() {
        assertEquals(14,
                evaluate("+", "2", "*", "3", "4"));
    }

    @Test
    public void testNestedSubtraction() {
        assertEquals(2,
                evaluate("-", "*", "2", "3", "4"));
    }

    @Test
    public void testMultipleDigitNumbers() {
        assertEquals(30, evaluate("+", "12", "18"));
    }

    @Test
    public void testNegativeResult() {
        assertEquals(-5, evaluate("-", "3", "8"));
    }

    @Test
    public void testIntegerDivision() {
        assertEquals(3, evaluate("/", "7", "2"));
    }

    @Test
    public void testDeeperNesting() {
        assertEquals(20,
                evaluate("*", "+", "2", "3", "-", "8", "4"));
    }
}
