class TrieNode {
    TrieNode[] children = new TrieNode[26]; // Array to hold child nodes
    boolean isEndOfWord = false; // Flag to mark the end of a word
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.isEndOfWord = true;
    }

    // Returns if the word is in the trie
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return node.isEndOfWord;
    }

    // Returns if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return false;
            }
            node = node.children[c - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        // Test the Trie implementation
        Trie trie = new Trie();

        // Insert words into the trie
        trie.insert("apple");
        trie.insert("app");

        // Test search method
        System.out.println(trie.search("apple")); // returns true
        System.out.println(trie.search("app"));   // returns true
        System.out.println(trie.search("appl"));  // returns false

        // Test startsWith method
        System.out.println(trie.startsWith("app"));  // returns true
        System.out.println(trie.startsWith("apl"));  // returns false
        System.out.println(trie.startsWith("apple")); // returns true
    }
}
