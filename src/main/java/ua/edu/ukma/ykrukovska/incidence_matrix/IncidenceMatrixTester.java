package ua.edu.ukma.ykrukovska.incidence_matrix;

import java.util.Arrays;

public class IncidenceMatrixTester {

    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";
    private static final String RESUlT_PATH = "D://Studying//InfortmationRetrieval//MatrixResult.txt";

    public static void main(String[] args) {
        IncidenceMatrixBuilder matrixBuilder = new IncidenceMatrixBuilder();
       // IncidenceMatrixWriter.writeInvertedIndex(matrixBuilder.createCollection(Arrays.asList(BOOK_PATH + "Test1.fb2", BOOK_PATH + "Test2.fb2")), RESUlT_PATH);
        IncidenceMatrixWriter.writeMatrix(matrixBuilder.createCollection(Arrays.asList(BOOK_PATH + "harrypotter1.fb2", BOOK_PATH + "harrypotter1.fb2",
                BOOK_PATH + "MonteCristo.fb2", BOOK_PATH + "DorianGray.fb2", BOOK_PATH + "JourneyEarth.fb2",
                BOOK_PATH + "TreasureIsland.fb2", BOOK_PATH + "AliceWonderland.fb2",
                BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GrimmsFairyTales.fb2")), RESUlT_PATH);
    }

}
