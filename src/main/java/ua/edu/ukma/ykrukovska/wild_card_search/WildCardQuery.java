package ua.edu.ukma.ykrukovska.wild_card_search;

import ua.edu.ukma.ykrukovska.permuterm_index.PermutermIndex;

import java.util.LinkedList;
import java.util.List;

public class WildCardQuery {

    private PrefixTree prefixTree;
    private SuffixTree suffixTree;
    private PermutermIndex permutermIndex;

    public WildCardQuery(PrefixTree prefixTree, SuffixTree suffixTree, PermutermIndex permutermIndex) {
        this.prefixTree = prefixTree;
        this.suffixTree = suffixTree;
        this.permutermIndex = permutermIndex;
    }

    public List<String> executeQuery(String query) {

        List<String> words = new LinkedList<>();


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

           // words = findMiddleWildCard(query);


        }


        return words;

    }

    public List<String> findMiddleWildCard(String query) {
        permutermIndex.generateRotations(query);
        StringBuilder str = new StringBuilder(query + "$");

        do {
            str.append(str.charAt(0));
            str.deleteCharAt(0);
        } while (str.charAt(str.length() - 1) != '*');

        String word = str.toString();
        String[] splitByIndexSymbol = word.split("\\$");
        List<String> wordsWithPrefix = prefixTree.getWordsWithPrefix(splitByIndexSymbol[1].substring(0, splitByIndexSymbol[1].length() - 1));
        List<String> indexWords = new LinkedList<>();
        List<String> result = new LinkedList<>();


        for (String wordWithPrefix : wordsWithPrefix) {
            indexWords.add(splitByIndexSymbol[0] + "$" + wordWithPrefix);
        }

        for (String index : indexWords) {
            if (permutermIndex.getPermutermIndex().containsKey(index)) {
                result.add(permutermIndex.getPermutermIndex().get(index));
            }
        }

        System.out.println(result);
        return result;
    }
}
