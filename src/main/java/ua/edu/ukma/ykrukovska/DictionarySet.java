package ua.edu.ukma.ykrukovska;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DictionarySet implements Dictionary {

    private Set<String> words;

    public DictionarySet() {
        this.words = new TreeSet<String>();
    }

    @Override
    public void add(String word) {
        Objects.requireNonNull(word, "Must be not null");
        words.add(word.toLowerCase());
    }

    @Override
    public boolean checkIfWordExists(String word) {
        return words.contains(word);
    }

    @Override
    public int size() {
        return words.size();
    }

    @Override
    public int getWordsCount() {
        return words.size();
    }

    @Override
    public Object[] getDictionaryWords() {
        return words.toArray();
    }
}
