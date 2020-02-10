package ua.edu.ukma.ykrukovska.permuterm_index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermutermIndex {


    private List<String> files;
    private Map<String, String> permutermIndex = new HashMap<>();

    public PermutermIndex(List<String> files) {
        this.files = new ArrayList<>(files);
    }


    public void generateRotations(String word) {

        StringBuilder str = new StringBuilder(word + "$");

        for (int i = 0; i < str.length(); i++) {
            str.append(str.charAt(0));
            str.deleteCharAt(0);
            permutermIndex.put(str.toString(), word);

        }

    }

    public Map<String, String> getPermutermIndex() {
        return permutermIndex;
    }


}
