package ua.edu.ukma.ykrukovska.wild_card_search;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ua.edu.ukma.ykrukovska.PathValues.FILES;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class WildCardQueryTester {


    public static void main(String[] args) {

        SuffixTree suffixTree = SufixTreeBuilder.createSuffixTree(FILES);
        PrefixTree prefixTree = PrefixTreeBuilder.createPrefixBuilder(FILES);

        WildCardQuery wildCardQuery = new WildCardQuery(prefixTree, suffixTree);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULT_PATH + "WildCardQueryResult.txt"))) {

            List<String> result = wildCardQuery.executeQuery("*lo");

            writer.write(String.valueOf(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
