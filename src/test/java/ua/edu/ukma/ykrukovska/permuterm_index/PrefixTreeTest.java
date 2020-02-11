package ua.edu.ukma.ykrukovska.permuterm_index;

import org.junit.Assert;
import org.junit.Test;
import ua.edu.ukma.ykrukovska.wild_card_search.PrefixTree;

import java.util.LinkedList;
import java.util.List;

public class PrefixTreeTest {



    @Test
    public void getOneCharPrefix() {

        PrefixTree tree = new PrefixTree();
        tree.add("cat");
        tree.add("bender");
        tree.add("dog");
        tree.add("crotia");
        tree.add("california");
        tree.add("cannabis");

        List<String> result = new LinkedList<>();
        result.add("cat");
        result.add("california");
        result.add("cannabis");
        result.add("crotia");

        Assert.assertEquals(result, tree.getWordsWithPrefix("c"));

    }

    @Test
    public void getTwoCharPrefix() {

        PrefixTree tree = new PrefixTree();
        tree.add("cat");
        tree.add("bender");
        tree.add("dog");
        tree.add("california");
        tree.add("cannabis");

        List<String> result = new LinkedList<>();
        result.add("cat");
        result.add("california");
        result.add("cannabis");

        Assert.assertEquals(result, tree.getWordsWithPrefix("ca"));

    }
}