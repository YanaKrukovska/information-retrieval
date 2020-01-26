package ua.edu.ukma.ykrukovska.incidence_matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IncidenceMatrixWriter {

    public static void writeMatrix(IncidenceMatrix matrix, String newFileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            for (int i = 0; i < matrix.getFiles().size(); i++) {

                writer.write(i + " = " + matrix.getFiles().get(i) + System.lineSeparator());
            }

            for (String name : matrix.getMatrix().keySet()) {
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
