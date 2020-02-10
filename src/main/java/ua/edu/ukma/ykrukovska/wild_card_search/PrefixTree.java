package ua.edu.ukma.ykrukovska.wild_card_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrefixTree {

    private PrefixTreeNode root;

    public PrefixTree() {
        root = new PrefixTreeNode();
    }

    public void add(String word) {
        Map<Character, PrefixTreeNode> links = root.getLinks();

        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            PrefixTreeNode node;
            if (links.containsKey(character)) {
                node = links.get(character);
            } else {
                node = new PrefixTreeNode(character);
                links.put(character, node);
            }

            links = node.getLinks();


            if (i == word.length() - 1 && links.isEmpty()) {
                node.setLeaf(true);
            }
            if (i == word.length() - 1) {
                node.setWord(true);
            }
        }
    }

    private PrefixTreeNode findNode(String word) {
        Map<Character, PrefixTreeNode> links = root.getLinks();
        PrefixTreeNode node = null;
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            if (links.containsKey(character)) {
                node = links.get(character);
                links = node.getLinks();
            } else {
                return null;
            }
        }

        return node;
    }


    public boolean search(String word) {
        PrefixTreeNode node = findNode(word);
        return node != null && node.isWord();
    }


    private boolean isWordWithPrefixPresent(String prefix) {
        return findNode(prefix) != null;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        if (!isWordWithPrefixPresent(prefix)) {
            return null;
        }
        List<String> words = new ArrayList<>();
        PrefixTreeNode node = findNode(prefix);
        StringBuilder prefixSequence = new StringBuilder(prefix);

        getWords(node, prefixSequence.deleteCharAt(prefixSequence.length() - 1), words);

        return words;

    }

    private void getWords(PrefixTreeNode node, StringBuilder charSequence, List<String> words) {


        charSequence.append(node.getNodeCharacter());
        if (node.isWord()) {
            words.add(charSequence.toString());
        }

        if (node.isLeaf()) {
            return;
        }

        for (Map.Entry<Character, PrefixTreeNode> pair : node.getLinks().entrySet()) {
            getWords(pair.getValue(), charSequence, words);
            charSequence.deleteCharAt(charSequence.length() - 1);
        }

    }




    class PrefixTreeNode {
        private Map<Character, PrefixTreeNode> links = new HashMap<>();
        private char character;
        private boolean isWord;
        private boolean isLeaf;

        public PrefixTreeNode() {
        }

        public PrefixTreeNode(char character) {
            this.character = character;
        }

        public boolean isLeaf() {
            return isLeaf;
        }

        public boolean isWord() {
            return isWord;
        }

        public void setLeaf(boolean leaf) {
            isLeaf = leaf;
        }

        public void setWord(boolean word) {
            isWord = word;
        }

        public Map<Character, PrefixTreeNode> getLinks() {
            return links;
        }

        public char getNodeCharacter() {
            return character;
        }
    }
}

