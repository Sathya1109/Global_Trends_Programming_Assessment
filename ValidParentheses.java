import java.util.Scanner;
import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c); // Push opening brackets onto stack
            } else {
                if (stack.isEmpty()) return false; // Stack should not be empty for a closing bracket
                char top = stack.pop();
                if (c == ')' && top != '(' || c == '}' && top != '{' || c == ']' && top != '[') {
                    return false; // Check for matching pair
                }
            }
        }
        return stack.isEmpty(); // Stack should be empty if all brackets are matched
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string with parentheses: ");
        String input = scanner.nextLine();
        scanner.close();

        ValidParentheses validator = new ValidParentheses();
        boolean isValid = validator.isValid(input);
        if (isValid) {
            System.out.println("The input string has valid parentheses.");
        } else {
            System.out.println("The input string does not have valid parentheses.");
        }
    }
}
