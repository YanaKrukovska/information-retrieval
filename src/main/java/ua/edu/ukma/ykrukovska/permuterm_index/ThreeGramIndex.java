package ua.edu.ukma.ykrukovska.permuterm_index;

import java.util.*;

public class ThreeGramIndex {
    private List<String> files;
    private Map<String, List<String>> threeGramIndex = new HashMap<>();

    public ThreeGramIndex(List<String> files) {
        this.files = new ArrayList<>(files);
    }

    public void add(String word) {

        StringBuilder str = new StringBuilder("$" + word + "$");


        for (int i = 0; i < str.length() - 2; i++) {

            if (threeGramIndex.containsKey(str.substring(i, i + 3))) {
                threeGramIndex.get(str.substring(i, i + 3)).add(word);
            } else {
                threeGramIndex.put(str.substring(i, i + 3), new LinkedList<>());
                threeGramIndex.get(str.substring(i, i + 3)).add(word);
            }

        }

    }

    public Map<String, List<String>> getThreeGramIndex() {
        return threeGramIndex;
    }
}