package ua.edu.ukma.ykrukovska.inverted_index;

import java.util.*;

public class InvertedIndex {

    private List<String> files;
    private Map<String, Map<String, List<Integer>>> invertedIndex = new HashMap<>();


    public InvertedIndex(List<String> files) {
        this.files = new ArrayList<>(files);
    }


    public Map<String, Map<String, List<Integer>>> getInvertedIndex() {
        return invertedIndex;
    }

    public void add(String word, String fileName, Integer wordPosition) {
        Objects.requireNonNull(word);
        Objects.requireNonNull(fileName);


        Map<String, List<Integer>> documents = invertedIndex.get(word);

        if (documents == null) {
            documents = new TreeMap<>();
        }
        List<Integer> positions = documents.get(fileName);

        if (positions == null) {
            positions = new LinkedList<>();
        }

        positions.add(wordPosition);
        documents.put(fileName, positions);

        invertedIndex.put(word, documents);


    }

    public List<String> getFiles() {
        return files;
    }
}
