import java.util.Stack;

public class PostFixEvaluator {
    //Method to evaluate a postfix expression and return the result
    public static double evaluatePostfix(String expression, double a, double b, double c, double d) {
        //Initialize a stack to hold operand values
        Stack<Double> stack = new Stack<>();

        //Loop through each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            //Skip whitespace characters (things like spaces and such)
            if (ch == ' ') {
            //If it's an alphabetic character, it's an operand
            } else if (Character.isAlphabetic(ch)) {
                double value = getValue(ch, a, b, c, d);
                stack.push(value);
            //If it's not an alphabetic character, it's an operator
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid postfix expression: insufficient operands");
                }
                //Pop operands from the stack and apply the operator
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = applyOperator(operand1, operand2, ch);
                stack.push(result);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid postfix expression: too many operands");
        }

        //The final result will be at the top of the stack
        return stack.pop();
    }

    //Method to get the value of an identifier
    private static double getValue(char identifier, double a, double b, double c, double d) {
        switch (identifier) {
            case 'a': return a;
            case 'b': return b;
            case 'c': return c;
            case 'd': return d;
            default: throw new IllegalArgumentException("Invalid identifier: " + identifier);
        }
    }

    //Method to apply an operator's operation to two operands
    private static double applyOperator(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+': return operand1 + operand2; //Addition
            case '-': return operand1 - operand2; //Subtraction
            case '*': return operand1 * operand2; //Multiplication
            case '/': //Division
                if (operand2 == 0) {
                    throw new ArithmeticException("Error: Division by zero");
                }
                return operand1 / operand2;
            case '^': return Math.pow(operand1, operand2); // Exponential
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}