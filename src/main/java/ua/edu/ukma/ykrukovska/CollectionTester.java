package ua.edu.ukma.ykrukovska;

import com.sun.javafx.runtime.SystemProperties;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CollectionTester {

    public static void main(String[] args) {
        List<String> fileNames = new LinkedList<>();
       // fileNames.add("D://Studying//InfortmationRetrieval//harrypotter1.fb2");
        fileNames.add("D://Studying//InfortmationRetrieval//PrideAndPrejudice.fb2");
        CollectionBuilder collectionBuilder = new CollectionBuilder();
        Collection collection = collectionBuilder.createCollection(fileNames);
        System.out.println("Total size: " + collection.getTotalSize());
        System.out.println("Total word count: " + collection.getTotalWordCount());
        System.out.println("Dictionary word count: " + collection.getDictionaryWordCount());


        //  BufferedWriter writer = null;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("D://Studying//InfortmationRetrieval//results.txt"))) {
            for (int i = 0; i < collection.getDictionary().getWordsCount(); i++) {
                writer.write(collection.getDictionary().getDictionaryWords()[i] + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
