package org.example.minidictionaryapp.model;

import java.util.Locale;

public class DictionaryBST {

    public static class Node {
        public String word;     // normalized key
        public String meaning;  // value
        public Node left, right;

        public Node(String word, String meaning) {
            this.word = word;
            this.meaning = meaning;
        }
    }

    private Node root;

    private String normalize(String word) {
        return word == null ? "" : word.trim().toLowerCase(Locale.ROOT);
    }

    // Feature 1: Insert
    public boolean insert(String wordRaw, String meaning) {
        String word = normalize(wordRaw);
        if (word.isEmpty()) return false;

        if (root == null) {
            root = new Node(word, meaning);
            return true;
        }

        Node cur = root;
        while (true) {
            int cmp = word.compareTo(cur.word);
            if (cmp == 0) return false;
            if (cmp < 0) {
                if (cur.left == null) {
                    cur.left = new Node(word, meaning);
                    return true;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = new Node(word, meaning);
                    return true;
                }
                cur = cur.right;
            }
        }
    }

    // Feature 2: Search
    public String search(String wordRaw) {
        String word = normalize(wordRaw);
        if (word.isEmpty()) return null;

        Node cur = root;
        while (cur != null) {
            int cmp = word.compareTo(cur.word);
            if (cmp == 0) return cur.meaning;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return null;
    }

    // Feature 3: Update
    public boolean update(String wordRaw, String newMeaning) {
        String word = normalize(wordRaw);
        if (word.isEmpty()) return false;

        Node cur = root;
        while (cur != null) {
            int cmp = word.compareTo(cur.word);
            if (cmp == 0) {
                cur.meaning = newMeaning;
                return true;
            }
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return false;
    }

    // Feature 4: Delete
    public boolean delete(String wordRaw) {
        String word = normalize(wordRaw);
        if (word.isEmpty() || search(word) == null) return false;
        root = deleteRecursive(root, word);
        return true;
    }

    private Node deleteRecursive(Node current, String word) {
        if (current == null) return null;

        int cmp = word.compareTo(current.word);
        if (cmp < 0) {
            current.left = deleteRecursive(current.left, word);
        } else if (cmp > 0) {
            current.right = deleteRecursive(current.right, word);
        } else {
            // Kasus 1 & 2: Leaf atau 1 Anak
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;

            // Kasus 3: 2 Anak (Cari Successor: Terkecil di kanan)
            Node successor = findMin(current.right);
            current.word = successor.word;
            current.meaning = successor.meaning;
            current.right = deleteRecursive(current.right, successor.word);
        }
        return current;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // TODO: Feature 5 (Traversal)
    /*
    public String inorderList() { return ""; }
    public String preorderList() { return ""; }
    public String postorderList() { return ""; }
    */
    // Feature 5: Traversal
    public String inorderList() {
        StringBuilder sb = new StringBuilder();
        inorderRecursive(root, sb);
        return sb.length() == 0 ? "Kamus Kosong." : sb.toString();
    }

    private void inorderRecursive(Node node, StringBuilder sb) {
        if (node != null) {
            inorderRecursive(node.left, sb);
            sb.append("• ").append(node.word).append(": ").append(node.meaning).append("\n");
            inorderRecursive(node.right, sb);
        }
    }

    public String preorderList() {
        StringBuilder sb = new StringBuilder();
        preorderRecursive(root, sb);
        return sb.length() == 0 ? "Kamus Kosong." : sb.toString();
    }

    private void preorderRecursive(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append("[").append(node.word).append("] ");
            preorderRecursive(node.left, sb);
            preorderRecursive(node.right, sb);
        }
    }

    public String postorderList() {
        StringBuilder sb = new StringBuilder();
        postorderRecursive(root, sb);
        return sb.length() == 0 ? "Kamus Kosong." : sb.toString();
    }

    private void postorderRecursive(Node node, StringBuilder sb) {
        if (node != null) {
            postorderRecursive(node.left, sb);
            postorderRecursive(node.right, sb);
            sb.append(node.word).append(" ");
        }
    }
}
