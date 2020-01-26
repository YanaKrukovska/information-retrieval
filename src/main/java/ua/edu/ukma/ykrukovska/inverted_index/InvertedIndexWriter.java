package ua.edu.ukma.ykrukovska.inverted_index;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InvertedIndexWriter {
      public static void writeInvertedIndex(InvertedIndex invertedIndex, String newFileName) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            for (int i = 0; i < invertedIndex.getFiles().size(); i++) {
                writer.write(i + " = " + invertedIndex.getFiles().get(i) + System.lineSeparator());
            }

            for (String name : invertedIndex.getInvertedIndex().keySet()) {
                writer.write(System.lineSeparator());
                writer.write(name + ": " + System.lineSeparator());

                for (String fileName : invertedIndex.getInvertedIndex().get(name).keySet()) {
                    List<Integer> values = invertedIndex.getInvertedIndex().get(name).get(fileName);
                    writer.write(invertedIndex.getFiles().indexOf(fileName) + ": ");
                    for (Integer value : values) {
                        writer.write(value + ", ");
                    }
                    writer.write(System.lineSeparator());
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
