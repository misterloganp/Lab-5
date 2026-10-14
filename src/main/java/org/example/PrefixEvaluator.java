package org.example;

import java.util.ArrayList;

/**
 * Evaluates legal prefix expressions containing integers
 * and the operators +, -, *, and /.
 */
public class PrefixEvaluator {

    /**
     * Evaluates a prefix expression stored as tokens.
     *
     * @param tokens the tokens in the expression, ending with ;
     * @return the integer result of the expression
     */
    public static int evaluate(ArrayList<Token> tokens) {
        int[] index = {0};
        return evaluateExpression(tokens, index);
    }

    /**
     * Recursively evaluates the next prefix expression.
     *
     * @param tokens the expression tokens
     * @param index the index of the next token
     * @return the evaluated integer value
     */
    private static int evaluateExpression(
            ArrayList<Token> tokens, int[] index) {

        Token token = tokens.get(index[0]);
        index[0]++;

        if (token instanceof NumToken) {
            return ((NumToken) token).value;
        }

        int operator = ((OpToken) token).getOperator();

        int left = evaluateExpression(tokens, index);
        int right = evaluateExpression(tokens, index);

        switch (operator) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
            default:
                throw new IllegalArgumentException(
                        "Unknown operator: " + (char) operator);
        }
    }
}
