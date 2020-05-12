package ua.edu.ukma.ykrukovska.wild_card_search;

import ua.edu.ukma.ykrukovska.permuterm_index.PermutermIndex;
import ua.edu.ukma.ykrukovska.permuterm_index.ThreeGramIndex;

import java.util.LinkedList;
import java.util.List;

public class WildCardQuery {

    private PrefixTree prefixTree;
    private SuffixTree suffixTree;
    private PermutermIndex permutermIndex;
    private ThreeGramIndex threeGramIndex;

    public WildCardQuery(PrefixTree prefixTree, SuffixTree suffixTree, PermutermIndex permutermIndex) {
        this.prefixTree = prefixTree;
        this.suffixTree = suffixTree;
        this.permutermIndex = permutermIndex;
    }

    public WildCardQuery(ThreeGramIndex threeGramIndex, PermutermIndex permutermIndex) {
        this.threeGramIndex = threeGramIndex;
        this.permutermIndex = permutermIndex;
    }


    public List<String> executeQuery(String query) {

        List<String> words;

        if (query.length() == 1) {
            return new LinkedList<>();
        }

        if (query.charAt(query.length() - 1) == '*' && query.charAt(0) == '*') {
            words = findMiddleWildCardPermutermIndex(query.substring(1, query.length() ));
        } else if (query.charAt(query.length() - 1) == '*') {
            words = prefixTree.getWordsWithPrefix(query.substring(0, query.length() - 1));
        } else if (query.charAt(0) == '*') {
            words = suffixTree.getWordsWithSuffix(query.substring(1, query.length()));
        } else {
            words = findMiddleWildCardPermutermIndex(query);
        }


        return words;

    }

    public List<String> findMiddleWildCardPermutermIndex(String query) {
        permutermIndex.generateRotations(query);
        StringBuilder str = new StringBuilder(query + "$");
        List<String> result = new LinkedList<>();

        do {
            str.append(str.charAt(0));
            str.deleteCharAt(0);
        } while (str.charAt(str.length() - 1) != '*');

        String word = str.toString();
        String[] splitByIndexSymbol = word.split("\\$");

        List<String> wordsWithPrefix = findWordsWithPrefix(splitByIndexSymbol[1]);
        List<String> indexWords;


        indexWords = splitByIndexSymbol[0].contains("*") ?
                getIndexWordsDoubleQuery(splitByIndexSymbol[0].split("\\*")[0], splitByIndexSymbol[0].split("\\*")[1], wordsWithPrefix) : getIndexWords(splitByIndexSymbol[0], wordsWithPrefix);


        addWordsPresentInPermutermIndex(result, indexWords);

        return result;
    }

    private List<String> getIndexWords(String s, List<String> wordsWithPrefix) {
        List<String> indexWords = new LinkedList<>();
        for (String wordWithPrefix : wordsWithPrefix) {
            String possibleWord = wordWithPrefix.substring(0, wordWithPrefix.length() - s.length());
            indexWords.add(s + "$" + possibleWord);

        }
        return indexWords;
    }

    private List<String> getIndexWordsDoubleQuery(String extra, String ending, List<String> wordsWithPrefix) {
        List<String> indexWords = new LinkedList<>();
        for (String wordWithPrefix : wordsWithPrefix) {
            String possibleWord = wordWithPrefix.substring(0, wordWithPrefix.length() - ending.length());
            if (possibleWord.contains(extra)) {
                indexWords.add(ending + "$" + possibleWord);
            }
        }
        return indexWords;
    }

    private List<String> findWordsWithPrefix(String prefix) {
        return prefixTree.getWordsWithPrefix(prefix.substring(0, prefix.length() - 1));
    }

    private void addWordsPresentInPermutermIndex(List<String> result, List<String> indexWords) {
        for (String index : indexWords) {
            if (permutermIndex.getPermutermIndex().containsKey(index)) {
                result.add(permutermIndex.getPermutermIndex().get(index));
            }
        }
    }


    public List<String> findMiddleWildCardThreeGram(String query) {


        String firstPartOfWord = query.split("\\*")[0];
        String query1 = "$" + firstPartOfWord.substring(0, 2);
        List<String> res1 = threeGramIndex.getThreeGramIndex().get(query1);
        List<String> res2;


        while (firstPartOfWord.length() >= 3) {

            String query2 = firstPartOfWord.substring(0, 3);
            res2 = threeGramIndex.getThreeGramIndex().get(query2);
            res1.retainAll(res2);

            StringBuilder str = new StringBuilder(firstPartOfWord);

            str.deleteCharAt(0);
            firstPartOfWord = str.toString();
        }

        if (query.split("\\*").length > 1) {

            String secondPartOfWord = query.split("\\*")[1];


            while (secondPartOfWord.length() >= 3) {

                String query2 = secondPartOfWord.substring(0, 3);
                res2 = threeGramIndex.getThreeGramIndex().get(query2);
                res1.retainAll(res2);

                StringBuilder str = new StringBuilder(secondPartOfWord);

                str.deleteCharAt(0);
                secondPartOfWord = str.toString();

                if (secondPartOfWord.length() == 2) {
                    query2 = secondPartOfWord + "$";
                    res2 = threeGramIndex.getThreeGramIndex().get(query2);
                    res1.retainAll(res2);
                }
            }
        }

        return res1;
    }
}
