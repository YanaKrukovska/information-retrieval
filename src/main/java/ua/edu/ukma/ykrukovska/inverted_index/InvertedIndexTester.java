package ua.edu.ukma.ykrukovska.inverted_index;

import ua.edu.ukma.ykrukovska.incidence_matrix.IncidenceMatrixBuilder;
import ua.edu.ukma.ykrukovska.incidence_matrix.IncidenceMatrixWriter;

import java.util.Arrays;

public class InvertedIndexTester {

    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";
    private static final String RESUlT_PATH = "D://Studying//InfortmationRetrieval//InvertedIndexResult.txt";

    public static void main(String[] args) {
        InvertedIndexBuilder invertedIndex = new InvertedIndexBuilder();
        InvertedIndexWriter.writeInvertedIndex(invertedIndex.createCollection(Arrays.asList(BOOK_PATH + "harrypotter1.fb2", BOOK_PATH + "PrideAndPrejudice.fb2",
                BOOK_PATH + "MonteCristo.fb2", BOOK_PATH + "DorianGray.fb2", BOOK_PATH + "JourneyEarth.fb2",
                BOOK_PATH + "TreasureIsland.fb2", BOOK_PATH + "AliceWonderland.fb2",
                BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GrimmsFairyTales.fb2")), RESUlT_PATH);



    }

}
