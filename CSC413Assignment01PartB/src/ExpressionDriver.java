//Name: Ryan Quach
//Date: 02/19/24

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpressionDriver {
    //The infix expression we are using, doesn't change so final
    private static final String infixExp = "(a+b)*(c+d)";
    //The postfix expression we are using, doesn't change so final
    private static final String postfixExp = "ac-b^d+";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                //Ask the user to assign values to the identifiers
                System.out.println("Enter values for identifiers (a, b, c, d):");
                System.out.print("a = ");
                double a = scanner.nextDouble();
                System.out.print("b = ");
                double b = scanner.nextDouble();
                System.out.print("c = ");
                double c = scanner.nextDouble();
                System.out.print("d = ");
                double d = scanner.nextDouble();

                //Calculate and print the result for the infix expression
                double infixResult = InfixEvaluator.evaluateInfix(infixExp
                        //Replace placeholders in the infix expression with provided values
                        .replace("a", Double.toString(a))
                        .replace("b", Double.toString(b))
                        .replace("c", Double.toString(c))
                        .replace("d", Double.toString(d)));
                //Print the result of the infix expression evaluation
                System.out.println("Value of infix string " + infixExp + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + infixResult);

                //Compute and print the result for the postfix expression
                double postfixResult = PostFixEvaluator.evaluatePostfix(postfixExp, a, b, c, d);
                //Print the result of the postfix expression evaluation
                System.out.println("Value of postfix string " + postfixExp + " with a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + " is " + postfixResult);

                //Ask if the user wants to continue
                System.out.println("Do you want to continue? (please choose 'yes' or 'no')");
                String choice = scanner.next();
                //If user chooses not to continue, end program
                if (!choice.equalsIgnoreCase("yes")) {
                    System.out.println("Exiting...");
                    break;
                }
            } catch (InputMismatchException e) {
                //If user enters invalid data type, handle it
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
