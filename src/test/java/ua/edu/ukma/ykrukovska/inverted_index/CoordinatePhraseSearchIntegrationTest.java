package ua.edu.ukma.ykrukovska.inverted_index;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;
import java.util.TreeSet;

import static ua.edu.ukma.ykrukovska.PathValues.FILES;

public class CoordinatePhraseSearchIntegrationTest {

    @Test
    public void findPhraseIntegrationTest() {
        InvertedIndex invertedIndex = InvertedIndexBuilder.createCollection(FILES);

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);

        Set<Integer> expectedResult = new TreeSet<>();
        expectedResult.add(8);


        Set<Integer> result = coordinatePhraseSearch.findExactPhrase("one of the men");
        Assert.assertEquals(expectedResult, result);

    }

}
