package ua.edu.ukma.ykrukovska.wild_card_search;

import org.junit.Assert;
import org.junit.Test;
import ua.edu.ukma.ykrukovska.permuterm_index.PermutermIndex;
import ua.edu.ukma.ykrukovska.permuterm_index.PermutermIndexBuilder;
import ua.edu.ukma.ykrukovska.permuterm_index.ThreeGramIndex;
import ua.edu.ukma.ykrukovska.permuterm_index.ThreeGramIndexBuilder;

import java.util.*;

import static ua.edu.ukma.ykrukovska.PathValues.BOOK_PATH;

public class WildCardQueryIntegrationTest {

    //Collections.singletonList(BOOK_PATH + "test4.fb2")


    @Test
    public void findSimpleMiddleQuery1() {
        SuffixTree suffixTree = SufixTreeBuilder.createSuffixTree(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PrefixTree prefixTree = PrefixTreeBuilder.createPrefixBuilder(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PermutermIndex permutermIndex = PermutermIndexBuilder.createCollection(Collections.singletonList(BOOK_PATH + "test4.fb2"));

        WildCardQuery wildCardQuery = new WildCardQuery(prefixTree, suffixTree, permutermIndex);


        List<String> result = new LinkedList<>();
        result.add("cats");
        result.add("caerjs");
        result.add("californias");
        result.add("cannabis");


        Assert.assertEquals(result, wildCardQuery.findMiddleWildCardPermutermIndex("ca*s"));

    }

    @Test
    public void findSimpleMiddleQuery2() {
        SuffixTree suffixTree = SufixTreeBuilder.createSuffixTree(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PrefixTree prefixTree = PrefixTreeBuilder.createPrefixBuilder(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PermutermIndex permutermIndex = PermutermIndexBuilder.createCollection(Collections.singletonList(BOOK_PATH + "test4.fb2"));

        WildCardQuery wildCardQuery = new WildCardQuery(prefixTree, suffixTree, permutermIndex);


        List<String> result = new LinkedList<>();
        result.add("yards");
        result.add("yachts");
        result.add("yages");
        result.add("yaninas");

        Assert.assertEquals(result, wildCardQuery.findMiddleWildCardPermutermIndex("ya*s"));

    }


    @Test
    public void findSimpleMiddleQuery3() {
        SuffixTree suffixTree = SufixTreeBuilder.createSuffixTree(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PrefixTree prefixTree = PrefixTreeBuilder.createPrefixBuilder(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PermutermIndex permutermIndex = PermutermIndexBuilder.createCollection(Collections.singletonList(BOOK_PATH + "test4.fb2"));

        WildCardQuery wildCardQuery = new WildCardQuery(prefixTree, suffixTree, permutermIndex);


        List<String> result = new LinkedList<>();
        result.add("swelling");
        result.add("swimming");
        result.add("swinging");
        result.add("sobbing");


        Assert.assertEquals(result, wildCardQuery.findMiddleWildCardPermutermIndex("s*ing"));

    }


    @Test
    public void findSimpleMiddleQuery4() {
        SuffixTree suffixTree = SufixTreeBuilder.createSuffixTree(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PrefixTree prefixTree = PrefixTreeBuilder.createPrefixBuilder(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PermutermIndex permutermIndex = PermutermIndexBuilder.createCollection(Collections.singletonList(BOOK_PATH + "test4.fb2"));

        WildCardQuery wildCardQuery = new WildCardQuery(prefixTree, suffixTree, permutermIndex);


        List<String> result = new LinkedList<>();
        result.add("swelling");
        result.add("swimming");
        result.add("swinging");


        Assert.assertEquals(result, wildCardQuery.findMiddleWildCardPermutermIndex("sw*ing"));

    }


    @Test
    public void findDoubleMiddleQuery() {
        SuffixTree suffixTree = SufixTreeBuilder.createSuffixTree(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PrefixTree prefixTree = PrefixTreeBuilder.createPrefixBuilder(Collections.singletonList(BOOK_PATH + "test4.fb2"));
        PermutermIndex permutermIndex = PermutermIndexBuilder.createCollection(Collections.singletonList(BOOK_PATH + "test4.fb2"));

        WildCardQuery wildCardQuery = new WildCardQuery(prefixTree, suffixTree, permutermIndex);


        List<String> result = new LinkedList<>();
        result.add("swimming");
        result.add("swinging");


        Assert.assertEquals(result, wildCardQuery.findMiddleWildCardPermutermIndex("s*i*ing"));

    }

     @Test
    public void findSimpleMiddleQuery1ThreeGram() {
        ThreeGramIndex threeGramIndex = ThreeGramIndexBuilder.createCollection((Collections.singletonList(BOOK_PATH + "test4.fb2")));
        PermutermIndex permutermIndex = PermutermIndexBuilder.createCollection(Collections.singletonList(BOOK_PATH + "test4.fb2"));

        WildCardQuery wildCardQuery = new WildCardQuery(threeGramIndex, permutermIndex);

        List<String> result = new LinkedList<>();
        result.add("swimming");
        result.add("swinging");
        result.add("swelling");

        Assert.assertEquals(result, wildCardQuery.findMiddleWildCardThreeGram("sw*ng"));

    }

    @Test
    public void findSimpleMiddleQuery2ThreeGram() {
        ThreeGramIndex threeGramIndex = ThreeGramIndexBuilder.createCollection((Collections.singletonList(BOOK_PATH + "test4.fb2")));
        PermutermIndex permutermIndex = PermutermIndexBuilder.createCollection(Collections.singletonList(BOOK_PATH + "test4.fb2"));

        WildCardQuery wildCardQuery = new WildCardQuery(threeGramIndex, permutermIndex);

        List<String> result = new LinkedList<>();
        result.add("swimming");
        result.add("swinging");

        Assert.assertEquals(result, wildCardQuery.findMiddleWildCardThreeGram("swi*"));

    }
}