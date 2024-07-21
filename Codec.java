import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Codec {

    // Encodes a tree to a single string
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
            } else {
                sb.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        String[] nodes = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 1; i < nodes.length; i++) {
            TreeNode parent = queue.poll();
            if (!nodes[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(nodes[i]));
                parent.left = left;
                queue.add(left);
            }
            if (!nodes[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(nodes[i]));
                parent.right = right;
                queue.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        // Test the Codec implementation
        Codec codec = new Codec();

        // Create a sample tree: 1
        //                     /   \
        //                    2     3
        //                         / \
        //                        4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // Serialize the tree
        String serialized = codec.serialize(root);
        System.out.println("Serialized tree: " + serialized);

        // Deserialize the string back to a tree
        TreeNode deserializedRoot = codec.deserialize(serialized);

        // Verify the deserialized tree structure
        System.out.println("Deserialized tree root value: " + deserializedRoot.val); // Should print 1
        System.out.println("Left child value: " + deserializedRoot.left.val); // Should print 2
        System.out.println("Right child value: " + deserializedRoot.right.val); // Should print 3
        System.out.println("Right-Left grandchild value: " + deserializedRoot.right.left.val); // Should print 4
        System.out.println("Right-Right grandchild value: " + deserializedRoot.right.right.val); // Should print 5
    }
}
