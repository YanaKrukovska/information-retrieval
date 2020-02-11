package ua.edu.ukma.ykrukovska.wild_card_search;

import org.junit.Assert;
import org.junit.Test;
import ua.edu.ukma.ykrukovska.permuterm_index.PermutermIndex;

import java.util.*;

public class WildCardQueryTest {

    @Test
    public void findMiddleWildCard() {

        PrefixTree prefixTree = new PrefixTree();
        prefixTree.add("animal");
        prefixTree.add("cat");
        prefixTree.add("cult");
        SuffixTree suffixTree = new SuffixTree();
        prefixTree.add("animal");
        suffixTree.add("cat");
        suffixTree.add("cult");

        PermutermIndex permutermIndex = new PermutermIndex(Collections.singletonList("1"));
        //permutermIndex.generateRotations("cat");
        //permutermIndex.generateRotations("cult");

        WildCardQuery wildCardQuery = new WildCardQuery(prefixTree, suffixTree, permutermIndex);

        Map<String, String> index = new HashMap<>();
        index.put("cat$", "cat");
        index.put("at$c", "cat");
        index.put("t$ca", "cat");
        index.put("$cat", "cat");
        index.put("cult$", "cult");
        index.put("ult$c", "cult");
        index.put("lt$cu", "cult");
        index.put("t$cul", "cult");
        index.put("$cult", "cult");

        List<String> result = new LinkedList<>();
        result.add("cat");
        result.add("cult");

        Assert.assertEquals(result, wildCardQuery.findMiddleWildCard("c*t"));


    }
}