package ua.edu.ukma.ykrukovska.biword_index;

import java.util.*;

public class BiwordIndex {

    private List<String> files;
    private Map<String, List<String>> biwordIndex = new HashMap<>();

    public BiwordIndex(List<String> files) {
        this.files = new ArrayList<>(files);
    }

    public void add(String fileName, String pair) {
        Objects.requireNonNull(fileName);
        Objects.requireNonNull(pair);

        List<String> phrases = biwordIndex.get(fileName);

        if (phrases == null) {
            phrases = new LinkedList<>();
        }


        phrases.add(pair);

        biwordIndex.put(fileName, phrases);


    }

    public Map<String, List<String>> getBiwordIndex() {
        return biwordIndex;
    }

    public List<String> getFiles() {
        return files;
    }
}
