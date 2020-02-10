package ua.edu.ukma.ykrukovska.wild_card_search;
import ua.edu.ukma.ykrukovska.wild_card_search.PrefixTree;
import ua.edu.ukma.ykrukovska.wild_card_search.SuffixTree;

import java.util.ArrayList;
import java.util.List;

public class WildCardQuery {

    private PrefixTree prefixTree;
    private SuffixTree suffixTree;

    public WildCardQuery(PrefixTree prefixTree, SuffixTree suffixTree) {
        this.prefixTree = prefixTree;
        this.suffixTree = suffixTree;
    }

    public List<String> executeQuery(String query) {

        List<String> words = new ArrayList<>();


            if (query.charAt(query.length() - 1) == '*') {
                words = prefixTree.getWordsWithPrefix(query.substring(0, query.length() - 1));
            } else if (query.charAt(0) == '*') {
                words = suffixTree.getWordsWithSuffix(query.substring(1, query.length()));
            } else {
                int pos = query.indexOf('*');
                List<String> wordsWithGivenPrefix = prefixTree.getWordsWithPrefix(query.substring(0, pos));
                List<String> wordsWithGivenSuffix = suffixTree.getWordsWithSuffix(query.substring(pos + 1, query.length()));
                wordsWithGivenPrefix.retainAll(wordsWithGivenSuffix);
                words = wordsWithGivenPrefix;
            }


        return words;

    }
}
