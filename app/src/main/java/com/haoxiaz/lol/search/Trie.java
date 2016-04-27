package com.haoxiaz.lol.search;

import java.util.ArrayList;
import java.util.List;

public class Trie<T> {

    private static class TrieNode<T> {
        private int R = 26;
        public TrieNode[] next;
        public T data;
        public boolean hasEnd = false;
        // Initialize your data structure here.
        public TrieNode() {
            next = new TrieNode[R];
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode<T>();
    }

    // Inserts a name into the trie.
    public void insert(String name, T data) {
        if (name == null || name.length()==0) return;
        put(root, name.toLowerCase(), 0, data);
    }

    private void put(TrieNode node, String name, int index, T data) {
        if (name.length()==index) {
            node.hasEnd = true;
            node.data = data;
            return;
        }
        char c = name.charAt(index);
        while (!isValidChar(c)) {
            c = name.charAt(++index);
        }
        if (node.next[c-'a'] == null) {
            node.next[c-'a'] = new TrieNode();
        }
        put(node.next[c-'a'], name, index+1, data);
    }

    private boolean isValidChar(char c) {
        return c-'a'>=0 && c-'a'<=25;
    }

    // Returns if the name is in the trie.
    public boolean search(String name) {
        if (name == null || name.length() == 0) return false;
        return search(root, name.toLowerCase(), 0);
    }

    private boolean search(TrieNode node, String name, int index) {
        if (index == name.length()) {
            return node.hasEnd;
        }
        char c = name.charAt(index);
        if (!isValidChar(c) || node.next[c-'a'] == null) return false;
        return search(node.next[c - 'a'], name, index + 1);
    }

    private boolean startsWith(TrieNode node, String name, int index) {
        if (index == name.length()) return true;
        char c = name.charAt(index);
        if (!isValidChar(c) || node.next[c-'a'] == null) return false;
        return startsWith(node.next[c - 'a'], name, index + 1);
    }

    // Returns if there is any name in the trie
    // that starts with the given prefix.
    public boolean startsWith(String name) {
        if (name == null || name.length() == 0) return false;
        return startsWith(root, name.toLowerCase(), 0);
    }

    public List<T> startsWithList(String name) {
        if (name == null || name.length() == 0) return null;
        List<T> re = new ArrayList<>();
        searchForStart(re, name.toLowerCase(), root, 0);
        return re;
    }

    private void getAllUnder(List<T> re, TrieNode node, String cur) {
        if (node.hasEnd) {
            re.add((T)node.data);
        }
        for (int i = 0; i<node.next.length; i++) {
            if (node.next[i]!=null) {
                char c = (char) (i+'a');
                String thisCur = cur + c;
                getAllUnder(re, node.next[i], thisCur);
            }
        }
    }

    private void searchForStart(List<T> re, String name, TrieNode node, int index) {
        if (index == name.length()) {
            getAllUnder(re, node, name);
            return;
        }
        char c = name.charAt(index);

        if (isValidChar(c) && node.next[c-'a'] != null) {
            searchForStart(re, name, node.next[c-'a'], index+1);
        }
    }
}