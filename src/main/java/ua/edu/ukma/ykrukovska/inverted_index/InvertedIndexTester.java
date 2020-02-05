package ua.edu.ukma.ykrukovska.inverted_index;

import ua.edu.ukma.ykrukovska.biword_index.BiwordPhraseSearch;
import ua.edu.ukma.ykrukovska.incidence_matrix.IncidenceMatrixBuilder;
import ua.edu.ukma.ykrukovska.incidence_matrix.IncidenceMatrixWriter;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class InvertedIndexTester {

    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";
    private static final String RESUlT_PATH = "D://Studying//InfortmationRetrieval//InvertedIndexResult.txt";

    public static void main(String[] args) {
        InvertedIndexBuilder invertedIndexBuilder = new InvertedIndexBuilder();
      InvertedIndex invertedIndex = invertedIndexBuilder.createCollection(Arrays.asList(BOOK_PATH + "harrypotter1.fb2", BOOK_PATH + "PrideAndPrejudice.fb2",
                BOOK_PATH + "MonteCristo.fb2", BOOK_PATH + "DorianGray.fb2", BOOK_PATH + "JourneyEarth.fb2",
                BOOK_PATH + "TreasureIsland.fb2", BOOK_PATH + "AliceWonderland.fb2",
                BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GrimmsFairyTales.fb2"));

      //  InvertedIndex invertedIndex = invertedIndexBuilder.createCollection(Arrays.asList(BOOK_PATH + "test2.fb2", BOOK_PATH + "CallOfWild.fb2"));

        InvertedIndexWriter.writeInvertedIndex(invertedIndex, RESUlT_PATH);

        for (int i = 0; i < invertedIndex.getFiles().size(); i++) {
            System.out.println((i + " = " + new File(invertedIndex.getFiles().get(i)).getName()));
        }

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Scanner in = new Scanner(System.in);
        Scanner exit = new Scanner(System.in);
        int toExit = 0;

        do {
            System.out.println("Insert search: ");
            String input = in.nextLine();
            System.out.println(coordinatePhraseSearch.findPhrase(input));
            System.out.println("Do y'all want to continue? 0 - no");
            toExit = exit.nextInt();
        } while (toExit != 0);


    }



}
