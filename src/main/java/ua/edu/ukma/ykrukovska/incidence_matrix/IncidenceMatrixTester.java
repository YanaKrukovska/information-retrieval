package ua.edu.ukma.ykrukovska.incidence_matrix;

import ua.edu.ukma.ykrukovska.BooleanSearcher;

import java.io.File;
import java.util.Scanner;

import static ua.edu.ukma.ykrukovska.PathValues.FILES;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class IncidenceMatrixTester {


    public static void main(String[] args) {
        IncidenceMatrixBuilder matrixBuilder = new IncidenceMatrixBuilder();
        IncidenceMatrix matrix = matrixBuilder.createCollection(FILES);

        IncidenceMatrixWriter.writeMatrix(matrix, RESULT_PATH + "MatrixResult.txt");

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
