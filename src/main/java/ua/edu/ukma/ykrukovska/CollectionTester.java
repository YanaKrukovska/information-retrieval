package ua.edu.ukma.ykrukovska;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static ua.edu.ukma.ykrukovska.PathValues.FILES;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class CollectionTester {

    public static void main(String[] args) {

        Collection collection = CollectionBuilder.createCollection(FILES);
        System.out.println("Total size: " + collection.getTotalSizeKB() + " KB");
        System.out.println("Total word count: " + collection.getTotalWordCount());
        System.out.println("Dictionary word count: " + collection.getDictionaryWordCount());


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_PATH + "DictionaryResult.txt"))) {
            for (int i = 0; i < collection.getDictionary().getWordsCount(); i++) {
                writer.write(collection.getDictionary().getDictionaryWords()[i] + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
