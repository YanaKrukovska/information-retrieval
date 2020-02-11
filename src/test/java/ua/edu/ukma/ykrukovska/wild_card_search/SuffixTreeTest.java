package ua.edu.ukma.ykrukovska.wild_card_search;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SuffixTreeTest {

    @Test
    public void getOneCharSuffix() {

        SuffixTree tree = new SuffixTree();
        tree.add("cat");
        tree.add("bender");
        tree.add("dog");
        tree.add("cola");
        tree.add("crotia");
        tree.add("california");
        tree.add("cannabis");

        List<String> result = new LinkedList<>();
        result.add("crotia");
        result.add("california");
        result.add("cola");

        Assert.assertEquals(result, tree.getWordsWithSuffix("a"));
    }


    @Test
    public void getTwoCharSuffix() {

        SuffixTree tree = new SuffixTree();
        tree.add("cat");
        tree.add("bender");
        tree.add("dog");
        tree.add("crotia");
        tree.add("california");
        tree.add("cannabis");

        List<String> result = new LinkedList<>();
        result.add("crotia");
        result.add("california");

        Assert.assertEquals(result, tree.getWordsWithSuffix("ia"));
    }
}