package ua.edu.ukma.ykrukovska.incidence_matrix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class IncidenceMatrixWriter {

    public static void writeMatrix(IncidenceMatrix matrix, String newFileName) {

        Set<String> treeSet = new TreeSet<>(matrix.getMatrix().keySet());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            for (int i = 0; i < matrix.getFiles().size(); i++) {
                writer.write(i + " = " + new File(matrix.getFiles().get(i)).getName() + System.lineSeparator());
            }

            for (String name : treeSet) {
                writer.write(name + " : ");
                List<Boolean> values = matrix.getMatrix().get(name);

                for (int i = 0; i < values.size(); i++) {
                    writer.write(i + ":" + convertBool(values.get(i)) + ", ");
                }
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int convertBool(Boolean boolResult) {
        if (boolResult) {
            return 1;
        } else {
            return 0;
        }
    }


}
