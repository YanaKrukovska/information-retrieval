package ua.edu.ukma.ykrukovska.incidence_matrix;

import java.util.*;

public class IncidenceMatrix {


    private List<String> files;
    private Map<String, List<Boolean>> matrix = new TreeMap<>();

    public IncidenceMatrix(List<String> files) {
        this.files = new ArrayList<>(files);
    }

    public Map<String, List<Boolean>> getMatrix() {
        return matrix;
    }

    public void add(String word, String fileName) {
        Objects.requireNonNull(word);
        Objects.requireNonNull(fileName);

        List<Boolean> row = matrix.get(word);
        if (row == null) {

            row = initMatrixRow();
            matrix.put(word, row);
        }

        row.set(files.indexOf(fileName), true);
    }

    private List<Boolean> initMatrixRow() {
        List<Boolean> matrixRow = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            matrixRow.add(false);
        }
        return matrixRow;
    }

    public List<String> getFiles() {
        return files;
    }
}
