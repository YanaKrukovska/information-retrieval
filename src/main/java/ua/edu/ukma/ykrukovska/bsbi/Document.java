package ua.edu.ukma.ykrukovska.bsbi;

import java.util.List;
import java.util.Set;

public class Document {

    private String name;
    private List<String> words;
    private Set<String> uniqueWords;


    public Document(String name, List<String> words) {
        this.name = name;
        this.words = words;
    }

    public Document(String name, Set<String> words) {
        this.name = name;
        this.uniqueWords = words;
    }


    public String getName() {
        return name;
    }

    public List<String> getWords() {
        return words;
    }

    public Set<String> getUniqueWords() {
        return uniqueWords;
    }
}
