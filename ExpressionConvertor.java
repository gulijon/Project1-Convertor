import javax.swing.*;
import java.util.EmptyStackException;
import java.util.Stack;

public class ExpressionConvertor {

    public String postfixPrefixConvertor(String text, boolean postfix) {
        //tokenize the string
        Stack<String> reverse = postfix ? tokenizePostfix(text) : tokenizePrefix(text);
        Stack <String> operands = new Stack <>();
        String token = "";

        //while the reversal stack is not empty:
        while (!reverse.empty() || operands.size() != 1) {
            try {
                //pop the next token from the reversal stack
                token = reverse.pop();

                //if it is an operand, push it onto the operand stack
                if (isOperand(token)) {
                    //...need an operand stack...
                    operands.push(token);
                }
                //else it is an operator
                else {
                    //pop two operands off of the operand stack
                    String op1 = operands.pop();
                    String op2 = operands.pop();

                    //create a string with the two operands followed the operator if not postfix
                    String combined = postfix ? (token + " " + op2 + " " + op1 + " ") : (op1 + " " + op2 + " " + token); //token is the operator
                    //push that string onto the operand stack
                    operands.push(combined);

                }
            } catch (EmptyStackException e) {
                e.printStackTrace();
                if (operands.size() > 0) {
                    JOptionPane.showMessageDialog(null, "Syntax Error: Stack is not yet empty of operands");
                } else if (!isOperand(token) && !isOperator(token)) {
                    JOptionPane.showMessageDialog(null, "Syntax Error: Stack is not yet empty of operands");
                } else {
                    JOptionPane.showMessageDialog(null, "Syntax Error: Empty stack encountered");
                }
                return null;
            }
        }
        ///reversal is now empty;
        //pop the postfix expression off the stack
        return operands.pop();
    }

    private Stack <String> tokenizePrefix(String rawText) {
        // Create a stack of characters
        String[] chars = rawText.split(" ");
        Stack <String> charStack = new Stack <>();

        if (chars.length == 1 && rawText.length() > 1) {
            for (int i = 0; i < rawText.length(); i++) {
                charStack.push(String.valueOf(rawText.charAt(i)));
            }
        } else {

            for (int i = 0; i < chars.length; i++) {
                charStack.push(chars[i]);
            }
        }

        return charStack;
    }

    private Stack <String> tokenizePostfix(String rawText) {
        // Create a stack of characters
        String[] chars = rawText.split(" ");
        Stack <String> charStack = new Stack <>();

        if (chars.length == 1 && rawText.length() > 1) {
            for (int i = rawText.length() - 1; i > -1; i--) {
                charStack.push(String.valueOf(rawText.charAt(i)));
            }
        } else {
            for (int i = chars.length - 1; i > -1; i--) {
                charStack.push(chars[i]);
            }
        }

        return charStack;
    }

    private boolean isOperand(String token) {
        //Write this! need to test the string to see if it's an operand; return true if it is, false if it isn't
        if (token == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(token);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isOperator(String token) {
        //Write this! need to test the string to see if it's an operator; return true if it is, false if it isn't
        return token.equalsIgnoreCase("+")
                || token.equalsIgnoreCase("-")
                || token.equalsIgnoreCase("*")
                || token.equalsIgnoreCase("/")
                || token.equalsIgnoreCase("^")
                || token.equalsIgnoreCase("%");
    }
}