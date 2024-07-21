import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Interval {
    int start, end;
    Interval(int s, int e) { start = s; end = e; }
}

class IntervalTree {
    private static class Node {
        Interval interval;
        Node left, right;
        int max;

        Node(Interval interval) {
            this.interval = interval;
            this.max = interval.end; // Maximum end value in the subtree
        }
    }

    private Node root;

    // Insert a new interval [start, end] into the tree
    public void insertInterval(int start, int end) {
        root = insert(root, new Interval(start, end));
    }

    private Node insert(Node node, Interval interval) {
        if (node == null) return new Node(interval);
        int l = node.interval.start;
        if (interval.start < l)
            node.left = insert(node.left, interval);
        else
            node.right = insert(node.right, interval);
        if (node.max < interval.end)
            node.max = interval.end;
        return node;
    }

    // Delete an interval [start, end] from the tree
    public void deleteInterval(int start, int end) {
        root = delete(root, new Interval(start, end));
    }

    private Node delete(Node root, Interval interval) {
        if (root == null) return null;

        if (interval.start < root.interval.start)
            root.left = delete(root.left, interval);
        else if (interval.start > root.interval.start)
            root.right = delete(root.right, interval);
        else if (interval.end == root.interval.end) {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            Node min = minValueNode(root.right);
            root.interval = min.interval;
            root.right = delete(root.right, min.interval);
        }
        root.max = Math.max(root.interval.end, Math.max(max(root.left), max(root.right)));
        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    private int max(Node node) {
        return node == null ? Integer.MIN_VALUE : node.max;
    }

    // Find all intervals that overlap with the given interval [start, end]
    public List<Interval> findOverlappingIntervals(int start, int end) {
        List<Interval> result = new ArrayList<>();
        findOverlapping(root, new Interval(start, end), result);
        return result;
    }

    private void findOverlapping(Node node, Interval interval, List<Interval> result) {
        if (node == null) return;
        if (doOverlap(node.interval, interval)) result.add(node.interval);
        if (node.left != null && node.left.max >= interval.start)
            findOverlapping(node.left, interval, result);
        findOverlapping(node.right, interval, result);
    }

    private boolean doOverlap(Interval i1, Interval i2) {
        return i1.start <= i2.end && i2.start <= i1.end;
    }
}

public class IntervalTreeExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntervalTree intervalTree = new IntervalTree();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Insert an interval");
            System.out.println("2. Delete an interval");
            System.out.println("3. Find overlapping intervals");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter start of the interval: ");
                    int insertStart = scanner.nextInt();
                    System.out.print("Enter end of the interval: ");
                    int insertEnd = scanner.nextInt();
                    intervalTree.insertInterval(insertStart, insertEnd);
                    System.out.println("Interval [" + insertStart + ", " + insertEnd + "] inserted.");
                    break;
                case 2:
                    System.out.print("Enter start of the interval to delete: ");
                    int deleteStart = scanner.nextInt();
                    System.out.print("Enter end of the interval to delete: ");
                    int deleteEnd = scanner.nextInt();
                    intervalTree.deleteInterval(deleteStart, deleteEnd);
                    System.out.println("Interval [" + deleteStart + ", " + deleteEnd + "] deleted.");
                    break;
                case 3:
                    System.out.print("Enter start of the interval to find overlaps: ");
                    int findStart = scanner.nextInt();
                    System.out.print("Enter end of the interval to find overlaps: ");
                    int findEnd = scanner.nextInt();
                    List<Interval> overlappingIntervals = intervalTree.findOverlappingIntervals(findStart, findEnd);
                    System.out.println("Overlapping intervals with [" + findStart + ", " + findEnd + "]:");
                    for (Interval interval : overlappingIntervals) {
                        System.out.println("[" + interval.start + ", " + interval.end + "]");
                    }
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
