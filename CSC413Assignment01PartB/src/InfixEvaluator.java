import java.util.Stack;

public class InfixEvaluator {
    //Method to evaluate an infix expression and return the result
    public static double evaluateInfix(String expression) {
        // Initialize stacks to hold operators and operand values
        Stack<Character> operatorStack = new Stack<>();
        Stack<Double> valueStack = new Stack<>();

        //Iterate through each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            //Skip whitespace characters (things like spaces and such)
            if (Character.isWhitespace(currentChar)) {
                continue;
            }

            //If the current character is a digit, parse the operand value and push it onto the value stack
            if (Character.isDigit(currentChar)) {
                StringBuilder operandBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    operandBuilder.append(expression.charAt(i++));
                }
                //Decrement i to account for the extra increment in the loop
                i--;
                double operandValue = Double.parseDouble(operandBuilder.toString());
                valueStack.push(operandValue);
                //If the current character is an opening parenthesis, push it onto the operator stack
            } else if (currentChar == '(') {
                operatorStack.push(currentChar);
                //If the current character is a closing parenthesis, process operators inside the parentheses
            } else if (currentChar == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    performOperation(operatorStack, valueStack);
                }
                // Discard the '('
                operatorStack.pop();
                //If the current character is an operator, process operators with higher levels of importance
            } else {
                while (!operatorStack.isEmpty() && hasMoreImportance(operatorStack.peek(), currentChar)) {
                    performOperation(operatorStack, valueStack);
                }
                operatorStack.push(currentChar); // Push the current operator onto the operator stack
            }
        }

        //Process any remaining operators in the stacks
        while (!operatorStack.isEmpty()) {
            performOperation(operatorStack, valueStack);
        }

        //Return the final result from the value stack
        return valueStack.pop();
    }

    //Method to perform an operation and store the result
    private static void performOperation(Stack<Character> operatorStack, Stack<Double> valueStack) {
        char operator = operatorStack.pop();
        double operand2 = valueStack.pop();
        double operand1 = valueStack.pop();
        double result = calculate(operand1, operand2, operator);
        valueStack.push(result);
    }

    //Method to calculate the result of an operation
    private static double calculate(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+': return operand1 + operand2; // Addition
            case '-': return operand1 - operand2; // Subtraction
            case '*': return operand1 * operand2; // Multiplication
            case '/': return operand1 / operand2; // Division
            default: throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    //Method to check if an operator has more importance than another
    private static boolean hasMoreImportance(char op1, char op2) {
        //Operators '*' and '/' have more importance than '+' and '-'
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }
}
