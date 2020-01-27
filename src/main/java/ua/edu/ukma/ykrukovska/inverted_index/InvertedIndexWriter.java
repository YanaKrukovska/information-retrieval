package ua.edu.ukma.ykrukovska.inverted_index;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InvertedIndexWriter {
    public static void writeInvertedIndex(InvertedIndex invertedIndex, String newFileName) {

        Set<String> treeSet = new TreeSet<>(invertedIndex.getInvertedIndex().keySet());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFileName))) {
            for (int i = 0; i < invertedIndex.getFiles().size(); i++) {
                writer.write(i + " = " + new File(invertedIndex.getFiles().get(i)).getName() + System.lineSeparator());
            }

            for (String name : treeSet) {
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
