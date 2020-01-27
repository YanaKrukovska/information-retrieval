package ua.edu.ukma.ykrukovska.incidence_matrix;

import ua.edu.ukma.ykrukovska.BooleanSearcher;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class IncidenceMatrixTester {

    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";
    private static final String RESUlT_PATH = "D://Studying//InfortmationRetrieval//MatrixResult.txt";

    public static void main(String[] args) {
        IncidenceMatrixBuilder matrixBuilder = new IncidenceMatrixBuilder();
        IncidenceMatrix matrix = matrixBuilder.createCollection(Arrays.asList(BOOK_PATH + "harrypotter1.fb2", BOOK_PATH + "PrideAndPrejudice.fb2",
                BOOK_PATH + "MonteCristo.fb2", BOOK_PATH + "DorianGray.fb2", BOOK_PATH + "JourneyEarth.fb2",
                BOOK_PATH + "TreasureIsland.fb2", BOOK_PATH + "AliceWonderland.fb2",
                BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GrimmsFairyTales.fb2"));

        IncidenceMatrixWriter.writeMatrix(matrix, RESUlT_PATH);

        for (int i = 0; i < matrix.getFiles().size(); i++) {
            System.out.println((i + " = " + new File(matrix.getFiles().get(i)).getName()));
        }


        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);
        Scanner in = new Scanner(System.in);
        Scanner exit = new Scanner(System.in);
        int toExit = 0;

        do {
            System.out.println("Insert search: ");
            String input = in.nextLine();
            System.out.println(booleanSearcher.identifyOperator(input));
            System.out.println("Do you want to continue? 0 - no");
            toExit = exit.nextInt();
        } while (toExit != 0);

    }

}
