package ua.edu.ukma.ykrukovska.biword_index;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;

public class BiwordIndexTester {

    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";
    private static final String RESUlT_PATH = "D://Studying//InfortmationRetrieval//BiWordResult.txt";

    public static void main(String[] args) {
        BiwordIndexBuilder biwordIndexBuilder = new BiwordIndexBuilder();
        BiwordIndex biwordIndex = biwordIndexBuilder.createBiwordIndex(Collections.singletonList(BOOK_PATH + "harrypotter1.fb2"));

        BiwordIndexWriter.writeBiwordIndex(biwordIndex, RESUlT_PATH);

        for (int i = 0; i < biwordIndex.getFiles().size(); i++) {
            System.out.println((i + " = " + new File(biwordIndex.getFiles().get(i)).getName()));
        }


    }

}
