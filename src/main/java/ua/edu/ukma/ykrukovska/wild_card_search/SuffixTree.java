package ua.edu.ukma.ykrukovska.wild_card_search;


import java.util.LinkedList;
import java.util.List;

public class SuffixTree extends PrefixTree {

    @Override
    public void add(String word) {
        super.add(reverse(word));
    }

    public List<String> getWordsWithSuffix(String suffix) {

        suffix = reverse(suffix);

        List<String> words = new LinkedList<>();
        for (String word : super.getWordsWithPrefix(suffix)) {
            words.add(reverse(word));
        }

        return words;
    }

    @Override
    public boolean search(String word) {
        return super.search(word);
    }

    private String reverse(String word) {
        return new StringBuilder(word).reverse().toString();
    }

}