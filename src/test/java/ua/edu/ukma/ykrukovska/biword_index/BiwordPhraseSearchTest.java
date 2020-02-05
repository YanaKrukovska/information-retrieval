package ua.edu.ukma.ykrukovska.biword_index;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BiwordPhraseSearchTest {

    @Test
    public void findPhraseOneFile() {

        BiwordIndex biwordIndex = new BiwordIndex(Arrays.asList("0", "1", "2"));
        biwordIndex.add("1", "I love");
        biwordIndex.add("0", "I love");
        biwordIndex.add("0", "love cats");
        biwordIndex.add("0", "cats and");
        biwordIndex.add("0", "and dogs");


        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);

        BiwordPhraseSearch biwordPhraseSearch = new BiwordPhraseSearch(biwordIndex);
        Assert.assertEquals(resultsList, biwordPhraseSearch.findPhrase("cats and dogs"));

    }

    @Test
    public void findPhraseTwoFiles() {

        BiwordIndex biwordIndex = new BiwordIndex(Arrays.asList("0", "1", "2"));
        biwordIndex.add("0", "I love");
        biwordIndex.add("0", "love cats");
        biwordIndex.add("0", "cats and");
        biwordIndex.add("0", "and dogs");
        biwordIndex.add("1", "cats and");
        biwordIndex.add("2", "cats and");
        biwordIndex.add("2", "and dogs");
        biwordIndex.add("2", "live together");


        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(2);

        BiwordPhraseSearch biwordPhraseSearch = new BiwordPhraseSearch(biwordIndex);
        Assert.assertEquals(resultsList, biwordPhraseSearch.findPhrase("cats and dogs"));

    }

    @Test
    public void findBigPhraseOneFile() {

        BiwordIndex biwordIndex = new BiwordIndex(Arrays.asList("0", "1", "2"));
        biwordIndex.add("0", "i love");
        biwordIndex.add("0", "love cats");
        biwordIndex.add("0", "cats and");
        biwordIndex.add("0", "and dogs");

        biwordIndex.add("1", "cats and");
        biwordIndex.add("1", "and dogs");

        biwordIndex.add("2", "i love");
        biwordIndex.add("2", "love cats");
        biwordIndex.add("2", "cats and");
        biwordIndex.add("2", "and dogs");
        biwordIndex.add("2", "live together");


        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(2);

        BiwordPhraseSearch biwordPhraseSearch = new BiwordPhraseSearch(biwordIndex);
        Assert.assertEquals(resultsList, biwordPhraseSearch.findPhrase("i love cats and dogs"));

    }

    @Test
    public void findBigPhraseTwoFiles() {

        BiwordIndex biwordIndex = new BiwordIndex(Arrays.asList("0", "1", "2", "3"));
        biwordIndex.add("0", "bite my");
        biwordIndex.add("0", "my lovely");
        biwordIndex.add("0", "lovely cats");

        biwordIndex.add("1", "metal ass");
        biwordIndex.add("1", "my shiny");
        biwordIndex.add("1", "shiny metal");

        biwordIndex.add("2", "cats and");
        biwordIndex.add("2", "live together");

        biwordIndex.add("3", "my bender");
        biwordIndex.add("3", "bender said");
        biwordIndex.add("3", "said bite");
        biwordIndex.add("3", "bite my");
        biwordIndex.add("3", "my shiny");
        biwordIndex.add("3", "shiny metal");
        biwordIndex.add("3", "metal ass");




        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(1);
        resultsList.add(3);

        BiwordPhraseSearch biwordPhraseSearch = new BiwordPhraseSearch(biwordIndex);
        Assert.assertEquals(resultsList, biwordPhraseSearch.findPhrase("my shiny metal ass"));

    }


    @Test
    public void findTwoWords() {

        BiwordIndex biwordIndex = new BiwordIndex(Arrays.asList("0", "1", "2", "3"));
        biwordIndex.add("0", "bite my");
        biwordIndex.add("0", "bender said");
        biwordIndex.add("0", "my lovely");

        biwordIndex.add("1", "metal ass");
        biwordIndex.add("1", "my shiny");
        biwordIndex.add("1", "shiny metal");

        biwordIndex.add("3", "my bender");
        biwordIndex.add("3", "bender said");
        biwordIndex.add("3", "said bite");

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(3);

        BiwordPhraseSearch biwordPhraseSearch = new BiwordPhraseSearch(biwordIndex);
        Assert.assertEquals(resultsList, biwordPhraseSearch.findPhrase("bender said"));

    }
}