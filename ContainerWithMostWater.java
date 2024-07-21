import java.util.Scanner;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, minHeight * (right - left)); // Calculate area
            if (height[left] < height[right]) {
                left++; // Move the left pointer
            } else {
                right--; // Move the right pointer
            }
        }
        return maxArea; // Return the maximum area found
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input size of the array
        System.out.print("Enter the number of heights: ");
        int n = scanner.nextInt();
        int[] height = new int[n];

        // Input heights
        System.out.println("Enter the heights:");
        for (int i = 0; i < n; i++) {
            height[i] = scanner.nextInt();
        }

        scanner.close();

        // Calculate max area
        ContainerWithMostWater solution = new ContainerWithMostWater();
        int maxArea = solution.maxArea(height);

        // Output the result
        System.out.println("The maximum area of water that can be contained is: " + maxArea);
    }
}
