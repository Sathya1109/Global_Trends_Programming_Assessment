import java.util.Scanner;

public class PalindromeCheck {

    public boolean isPalindrome(String s) {
        if (s == null) return false;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false; // Characters do not match
            }
            left++;
            right--;
        }
        return true; // All characters matched
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PalindromeCheck palindromeCheck = new PalindromeCheck();

        System.out.print("Enter a string to check if it is a palindrome: ");
        String input = scanner.nextLine();

        scanner.close();

        boolean result = palindromeCheck.isPalindrome(input);

        if (result) {
            System.out.println("The input string \"" + input + "\" is a palindrome.");
        } else {
            System.out.println("The input string \"" + input + "\" is not a palindrome.");
        }
    }
}
