package ua.edu.ukma.ykrukovska.inverted_index;

import java.io.File;
import java.util.Scanner;

import static ua.edu.ukma.ykrukovska.PathValues.FILES;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class InvertedIndexTester {


    public static void main(String[] args) {
        InvertedIndex invertedIndex = InvertedIndexBuilder.createCollection(FILES);

        InvertedIndexWriter.writeInvertedIndex(invertedIndex, RESULT_PATH + "InvertedIndexResult.txt");

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
