package ua.edu.ukma.ykrukovska.dictionary;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ua.edu.ukma.ykrukovska.PathValues.*;

public class CollectionTester {

    public static void main(String[] args) {

        Collection collection = CollectionBuilder.createCollection(FILES_SH);

        System.out.println("Total size: " + collection.getTotalSizeKB() + " KB");
        System.out.println("Total word countAmount: " + collection.getTotalWordCount());
        System.out.println("Dictionary word countAmount: " + collection.getDictionaryWordCount());


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_PATH + "DictionaryResult.txt"))) {
            for (int i = 0; i < collection.getDictionary().getWordsCount(); i++) {
                writer.write(collection.getDictionary().getDictionaryWords()[i] + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
