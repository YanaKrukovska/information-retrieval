package ua.edu.ukma.ykrukovska.biword_index;

import ua.edu.ukma.ykrukovska.BooleanSearcher;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BiwordIndexTester {

    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";
    private static final String RESUlT_PATH = "D://Studying//InfortmationRetrieval//BiWordResult.txt";

    public static void main(String[] args) {
        BiwordIndexBuilder biwordIndexBuilder = new BiwordIndexBuilder();
        BiwordIndex biwordIndex = biwordIndexBuilder.createBiwordIndex(Arrays.asList(BOOK_PATH + "AliceWonderland.fb2",
                BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "harrypotter1.fb2"));

      //  BiwordIndexWriter.writeBiwordIndex(biwordIndex, RESUlT_PATH);

        for (int i = 0; i < biwordIndex.getFiles().size(); i++) {
            System.out.println((i + " = " + new File(biwordIndex.getFiles().get(i)).getName()));
        }

        BiwordPhraseSearch biwordPhraseSearch = new BiwordPhraseSearch(biwordIndex);
        Scanner in = new Scanner(System.in);
        Scanner exit = new Scanner(System.in);
        int toExit = 0;

        do {
            System.out.println("Insert search: ");
            String input = in.nextLine();
            System.out.println(biwordPhraseSearch.findPhrase(input));
            System.out.println("Do you want to continue? 0 - no");
            toExit = exit.nextInt();
        } while (toExit != 0);


    }

}
