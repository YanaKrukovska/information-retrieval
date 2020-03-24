package ua.edu.ukma.ykrukovska.inverted_index;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CoordinatePhraseSearchTest {

    @Test
    public void findTwoWordPhraseOneFile() {

        InvertedIndex invertedIndex = new InvertedIndex(Arrays.asList("0", "1"));
        invertedIndex.add("i", "0", 0);
        invertedIndex.add("cats", "0", 1);

        invertedIndex.add("i", "1", 1);
        invertedIndex.add("love", "1", 2);
        invertedIndex.add("cats", "1", 3);
        invertedIndex.add("and", "1", 4);
        invertedIndex.add("dogs", "1", 5);

        Set<Integer> resultsList = new TreeSet<>();
        resultsList.add(1);

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("cats and"));
    }

    @Test
    public void findTwoWordPhraseTwoFiles() {

        InvertedIndex invertedIndex = new InvertedIndex(Arrays.asList("0", "1"));
        invertedIndex.add("i", "0", 0);
        invertedIndex.add("cats", "0", 2);
        invertedIndex.add("and", "0", 3);
        invertedIndex.add("cats", "0", 4);

        invertedIndex.add("i", "1", 3);
        invertedIndex.add("love", "1", 4);
        invertedIndex.add("cats", "1", 5);
        invertedIndex.add("and", "1", 6);
        invertedIndex.add("dogs", "1", 7);
        invertedIndex.add("luv", "1", 8);

        Set<Integer> resultsList = new TreeSet<>();
        resultsList.add(0);
        resultsList.add(1);

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("cats and"));
    }

    @Test
    public void findThreeWordPhraseOneFile() {

        InvertedIndex invertedIndex = new InvertedIndex(Arrays.asList("0", "1", "2"));

        invertedIndex.add("i", "0", 1);
        invertedIndex.add("love", "0", 2);
        invertedIndex.add("cats", "0", 3);
        invertedIndex.add("and", "0", 4);
        invertedIndex.add("dogs", "0", 5);

        invertedIndex.add("i", "1", 0);
        invertedIndex.add("cats", "1", 1);

        invertedIndex.add("cats", "2", 8);
        invertedIndex.add("and", "2", 9);
        invertedIndex.add("cats", "2", 3);
        invertedIndex.add("and", "2", 4);
        invertedIndex.add("dogs", "2", 5);


        Set<Integer> resultsList = new TreeSet<>();
        resultsList.add(0);
        resultsList.add(2);

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("cats and dogs"));
    }

    @Test
    public void findThreeWordPhraseTwoFiles() {

        InvertedIndex invertedIndex = new InvertedIndex(Arrays.asList("0", "1", "2", "3"));

        invertedIndex.add("i", "0", 1);
        invertedIndex.add("love", "0", 2);
        invertedIndex.add("cats", "0", 3);
        invertedIndex.add("and", "0", 4);
        invertedIndex.add("dogs", "0", 5);

        invertedIndex.add("i", "1", 0);
        invertedIndex.add("i", "1", 4);
        invertedIndex.add("i", "1", 6);
        invertedIndex.add("i", "1", 9);
        invertedIndex.add("cats", "1", 1);
        invertedIndex.add("cats", "1", 2);
        invertedIndex.add("cats", "1", 99);

        invertedIndex.add("cats", "2", 8);
        invertedIndex.add("and", "2", 9);
        invertedIndex.add("dogs", "2", 5);

        invertedIndex.add("cats", "3", 2);
        invertedIndex.add("cats", "3", 3);
        invertedIndex.add("cats", "3", 6);
        invertedIndex.add("and", "3", 7);
        invertedIndex.add("dogs", "3", 8);

        Set<Integer> resultsList = new TreeSet<>();
        resultsList.add(0);
        resultsList.add(3);

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("cats and dogs"));
    }

    @Test
    public void wordDontExist() {

        InvertedIndex invertedIndex = new InvertedIndex(Arrays.asList("0", "1", "2", "3", "4", "5"));

        invertedIndex.add("i", "2", 1);
        invertedIndex.add("i", "4", 1);
        invertedIndex.add("i", "5", 1);

        invertedIndex.add("love", "1", 2);
        invertedIndex.add("love", "4", 2);
        invertedIndex.add("love", "5", 2);

        invertedIndex.add("dogs", "1", 3);
        invertedIndex.add("dogs", "4", 3);
        invertedIndex.add("dogs", "5", 3);


        Set<Integer> resultsList = new TreeSet<>();

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("you love dogs"));
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("i hate dogs"));
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("i love cats"));
    }



    @Test
    public void lastWordDontExist() {

        InvertedIndex invertedIndex = new InvertedIndex(Arrays.asList("0", "1", "2", "3", "4", "5"));

        invertedIndex.add("i", "1", 1);
        invertedIndex.add("i", "4", 4);
        invertedIndex.add("i", "5", 7);

        invertedIndex.add("love", "1", 2);
        invertedIndex.add("love", "4", 5);
        invertedIndex.add("love", "4", 6);
        invertedIndex.add("love", "5", 8);

        invertedIndex.add("dogs", "1", 3);
        invertedIndex.add("dogs", "4", 9);
        invertedIndex.add("dogs", "5", 9);


        Set<Integer> resultsList = new TreeSet<>();
        resultsList.add(1);
        resultsList.add(5);

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("i love dogs"));

    }



    @Test
    public void findThreeWordPhrase() {

        InvertedIndex invertedIndex = new InvertedIndex(Arrays.asList("0", "1", "2", "3", "4", "5"));

        invertedIndex.add("i", "2", 1);
        invertedIndex.add("i", "4", 1);
        invertedIndex.add("i", "5", 1);

        invertedIndex.add("love", "1", 2);
        invertedIndex.add("love", "4", 2);
        invertedIndex.add("love", "5", 2);

        invertedIndex.add("cats", "1", 3);
        invertedIndex.add("cats", "4", 3);
        invertedIndex.add("cats", "5", 3);


        Set<Integer> resultsList = new TreeSet<>();
        resultsList.add(4);
        resultsList.add(5);

        CoordinatePhraseSearch coordinatePhraseSearch = new CoordinatePhraseSearch(invertedIndex);
        Assert.assertEquals(resultsList, coordinatePhraseSearch.findExactPhrase("i love cats"));
    }
}