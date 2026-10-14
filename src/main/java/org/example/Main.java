package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    private static Boolean endsSequence(Token tok) {
        if (tok == null) return true;
        if (tok instanceof OpToken) {
            if (((OpToken) tok).getOperator() == ';') return true;
            if (((OpToken) tok).getOperator() == '.') return true;
        }
        return false;
    }
    private static ArrayList<Token> getNextSequence(Tokenizer toknizr) throws IOException {
        ArrayList<Token> tokens = new ArrayList<>();
        Token tok;
        do {
            tok = toknizr.nextToken();
            if (tok != null) tokens.add(tok);
        } while (!endsSequence(tok));
        if (tok == null) return null;
        if (tok instanceof OpToken) {
            if (((OpToken) tok).getOperator() == '.') {
                return null;
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        try {
            Tokenizer myScanner = new Tokenizer(System.in);
            ArrayList<Token> tokenSequence;

            do {
                System.out.print("Equation: ");
                tokenSequence = getNextSequence(myScanner);

                if (tokenSequence != null) {
                    int result = PrefixEvaluator.evaluate(tokenSequence);
                    System.out.println("Result: " + result);
                }
            } while (tokenSequence != null);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}