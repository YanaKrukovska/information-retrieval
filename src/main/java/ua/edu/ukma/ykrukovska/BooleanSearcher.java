package ua.edu.ukma.ykrukovska;

import ua.edu.ukma.ykrukovska.incidence_matrix.IncidenceMatrix;

import java.util.LinkedList;
import java.util.List;

public class BooleanSearcher {


    private IncidenceMatrix incidenceMatrix;


    public BooleanSearcher(IncidenceMatrix incidenceMatrix) {
        this.incidenceMatrix = incidenceMatrix;
    }

    public static String[] splitQuery(String input) {
        return input.split(" ");
    }

    public List<Integer> identifyOperator(String input) {

        switch (splitQuery(input)[1].toLowerCase()) {
            case "and":
                return findTwoWords(splitQuery(input)[0].toLowerCase(), splitQuery(input)[2].toLowerCase());
            case "or":
                return findOneWordOrAnother(splitQuery(input)[0].toLowerCase(), splitQuery(input)[2].toLowerCase());
            case "not":
                return findOneWordWithoutAnother(splitQuery(input)[0].toLowerCase(), splitQuery(input)[2].toLowerCase());
        }
        return null;
    }

    public List<Integer> findWord(String word) {

        List<Boolean> list = incidenceMatrix.getMatrix().get(word);
        if (list == null) {
            list = new LinkedList<>();
        }
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)) {
                result.add(i);
            }
        }

        return result;
    }

    public List<Integer> findTwoWords(String word1, String word2) {

        List<Integer> wordList1 = findWord(word1);
        List<Integer> wordList2 = findWord(word2);
        List<Integer> result = new LinkedList<>();

        if (wordList1.size() > wordList2.size()) {
            for (int i = 0; i < wordList1.size(); i++) {
                if (wordList2.contains(wordList1.get(i))) {
                    result.add(i);
                }
            }
        } else {
            for (int i = 0; i < wordList2.size(); i++) {
                if (wordList1.contains(wordList2.get(i))) {
                    result.add(i);
                }
            }
        }


        return result;
    }


    public List<Integer> findOneWordWithoutAnother(String wordToKeep, String wordToExclude) {

        List<Integer> wordToKeepList = findWord(wordToKeep);
        List<Integer> wordToExcludeList = findWord(wordToExclude);

        for (Integer value : wordToExcludeList) {
            wordToKeepList.remove(value);
        }

        return wordToKeepList;
    }

    public List<Integer> findOneWordOrAnother(String word1, String word2) {
        List<Integer> wordList1 = findWord(word1);
        List<Integer> wordList2 = findWord(word2);
        List<Integer> result = new LinkedList<>(wordList1);

        for (Integer value : wordList2) {
            if (!wordList1.contains(value))
                result.add(value);
        }

        return result;
    }
}
