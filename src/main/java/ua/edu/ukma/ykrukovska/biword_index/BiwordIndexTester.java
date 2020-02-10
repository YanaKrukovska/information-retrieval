package ua.edu.ukma.ykrukovska.biword_index;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class BiwordIndexTester {

    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";

    public static void main(String[] args) {
        BiwordIndex biwordIndex = BiwordIndexBuilder.createBiwordIndex(Arrays.asList(BOOK_PATH + "AliceWonderland.fb2",
                BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "harrypotter1.fb2"));

     // BiwordIndexWriter.writeBiwordIndex(biwordIndex, RESULT_PATH + "BiWordResult.txt");

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
