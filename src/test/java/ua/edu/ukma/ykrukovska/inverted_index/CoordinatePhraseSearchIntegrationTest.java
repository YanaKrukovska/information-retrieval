package ua.edu.ukma.ykrukovska.inverted_index;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class CoordinatePhraseSearchIntegrationTest {
    private static final String BOOK_PATH = "D://Studying//InfortmationRetrieval//fb2//";

    @Test
    public void findPhraseIntegrationTest() {
        InvertedIndexBuilder invertedIndexBuilder = new InvertedIndexBuilder();
        InvertedIndex invertedIndex = invertedIndexBuilder.createCollection(Arrays.asList(BOOK_PATH + "harrypotter1.fb2", BOOK_PATH + "PrideAndPrejudice.fb2",
                BOOK_PATH + "MonteCristo.fb2", BOOK_PATH + "DorianGray.fb2", BOOK_PATH + "JourneyEarth.fb2",
                BOOK_PATH + "TreasureIsland.fb2", BOOK_PATH + "AliceWonderland.fb2",
                BOOK_PATH + "GulliversTravels.fb2", BOOK_PATH + "CallOfWild.fb2", BOOK_PATH + "GrimmsFairyTales.fb2"));

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);

        Set<Integer> expectedResult = new TreeSet<>();
        expectedResult.add(8);


        Set<Integer> result = coordinatePhraseSearch.findPhrase("one of the men");
        Assert.assertEquals(expectedResult, result);

    }

}
