package ua.edu.ukma.ykrukovska;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CollectionTester {


    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";
    private static final String RESULT = "D://Studying//InfortmationRetrieval//results.txt";

    public static void main(String[] args) {
        List<String> fileNames = new LinkedList<>();
        fileNames.add(BOOK_PATH + "harrypotter1.fb2");
        fileNames.add(BOOK_PATH + "PrideAndPrejudice.fb2");
        fileNames.add(BOOK_PATH + "MonteCristo.fb2");
        fileNames.add(BOOK_PATH + "DorianGray.fb2");
        fileNames.add(BOOK_PATH + "JourneyEarth.fb2");
        fileNames.add(BOOK_PATH + "TreasureIsland.fb2");
        fileNames.add(BOOK_PATH + "AliceWonderland.fb2");
        fileNames.add(BOOK_PATH + "GulliversTravels.fb2");
        fileNames.add(BOOK_PATH + "CallOfWild.fb2");
        fileNames.add(BOOK_PATH + "GrimmsFairyTales.fb2");
        CollectionBuilder collectionBuilder = new CollectionBuilder();
        Collection collection = collectionBuilder.createCollection(fileNames);
        System.out.println("Total size: " + collection.getTotalSizeKB() + " KB");
        System.out.println("Total word count: " + collection.getTotalWordCount());
        System.out.println("Dictionary word count: " + collection.getDictionaryWordCount());


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT))) {
            for (int i = 0; i < collection.getDictionary().getWordsCount(); i++) {
                writer.write(collection.getDictionary().getDictionaryWords()[i] + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
