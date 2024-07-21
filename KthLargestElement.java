import java.util.PriorityQueue;
import java.util.Scanner;

public class KthLargestElement {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k); // Min-heap with size k
        for (int num : nums) {
            pq.offer(num); // Add elements to the heap
            if (pq.size() > k) {
                pq.poll(); // Remove the smallest element if heap size exceeds k
            }
        }
        return pq.peek(); // The root of the heap is the kth largest element
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input size of the array
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];

        // Input array elements
        System.out.println("Enter the array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Input k
        System.out.print("Enter the value of k: ");
        int k = scanner.nextInt();

        scanner.close();

        // Calculate the kth largest element
        KthLargestElement solution = new KthLargestElement();
        int kthLargest = solution.findKthLargest(nums, k);

        // Output the result
        System.out.println("The " + k + "th largest element in the array is: " + kthLargest);
    }
}
