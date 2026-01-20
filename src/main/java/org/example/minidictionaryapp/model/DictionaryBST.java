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

    // TODO: Feature 4 (Delete)
    /*
    public boolean delete(String wordRaw) {
        return false;
    }
    */

    // TODO: Feature 5 (Traversal)
    /*
    public String inorderList() { return ""; }
    public String preorderList() { return ""; }
    public String postorderList() { return ""; }
    */
}
