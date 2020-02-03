package ua.edu.ukma.ykrukovska;

import java.util.Arrays;
import java.util.Objects;

public class DictionaryArray implements Dictionary {

    private static final int INITIAL_SIZE = 1;
    private static final int RESIZE_AMOUNT = 2;
    private String[] dictionaryWords;
    private int wordsCount = 0;

    public DictionaryArray() {
        this.dictionaryWords = new String[INITIAL_SIZE];
    }

    @Override
    public void add(String word) {
        Objects.requireNonNull(word, "Must be not null");
        word = word.toLowerCase();
        if (checkIfWordIsNew(word)) {
            dictionaryWords[wordsCount++] = word;
            Arrays.sort(dictionaryWords, 0, wordsCount);
            if (checkIfRequiresResize()) {
                resizeArray();
            }
        }
    }

    @Override
    public boolean checkIfWordExists(String word) {
        word = word.toLowerCase();
        return Arrays.binarySearch(dictionaryWords, 0, wordsCount, word) >= 0;
    }

    private boolean checkIfWordIsNew(String word) {
        return (wordsCount == 0) || Arrays.binarySearch(dictionaryWords, 0, wordsCount, word) < 0;

    }


    public void add(String[] words) {
        for (String word : words) {
            add(word);
        }
    }


    private boolean checkIfRequiresResize() {
        return dictionaryWords.length == wordsCount;
    }

    @Override
    public int size() {
        return dictionaryWords.length;
    }

    private void resizeArray() {
        String[] newDictionary = new String[dictionaryWords.length * RESIZE_AMOUNT];
        System.arraycopy(dictionaryWords, 0, newDictionary, 0, dictionaryWords.length);
        dictionaryWords = newDictionary;
    }

    @Override
    public int getWordsCount() {
        return wordsCount;
    }

    @Override
    public Object[] getDictionaryWords() {
        return dictionaryWords;
    }
}
