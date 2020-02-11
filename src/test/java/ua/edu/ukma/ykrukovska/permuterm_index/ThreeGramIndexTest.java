package ua.edu.ukma.ykrukovska.permuterm_index;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class ThreeGramIndexTest {

    @Test
    public void addOneWord() {
        ThreeGramIndex threeGramIndex = new ThreeGramIndex(Collections.singletonList("1"));
        threeGramIndex.add("cat");

        Map<String, List<String>> result = new HashMap<>();
        List<String> catList = new LinkedList<>();
        catList.add("cat");
        result.put("$ca", catList);
        result.put("cat", catList);
        result.put("at$", catList);
        Assert.assertEquals(result, threeGramIndex.getThreeGramIndex());
    }

    @Test
    public void addTwoShortWords() {
        ThreeGramIndex threeGramIndex = new ThreeGramIndex(Collections.singletonList("1"));
        threeGramIndex.add("cat");
        threeGramIndex.add("dog");

        Map<String, List<String>> result = new HashMap<>();
        List<String> catList = new LinkedList<>();
        List<String> dogList = new LinkedList<>();
        catList.add("cat");
        dogList.add("dog");

        result.put("$ca", catList);
        result.put("cat", catList);
        result.put("at$", catList);

        result.put("$do", dogList);
        result.put("dog", dogList);
        result.put("og$", dogList);
        Assert.assertEquals(result, threeGramIndex.getThreeGramIndex());
    }


    @Test
    public void addTwoWords() {
        ThreeGramIndex threeGramIndex = new ThreeGramIndex(Collections.singletonList("1"));
        threeGramIndex.add("cat");
        threeGramIndex.add("kitten");

        Map<String, List<String>> result = new HashMap<>();
        List<String> catList = new LinkedList<>();
        List<String> kittenList = new LinkedList<>();
        catList.add("cat");
        kittenList.add("kitten");

        result.put("$ca", catList);
        result.put("cat", catList);
        result.put("at$", catList);

        result.put("$ki", kittenList);
        result.put("kit", kittenList);
        result.put("itt", kittenList);
        result.put("tte", kittenList);
        result.put("ten", kittenList);
        result.put("en$", kittenList);
        Assert.assertEquals(result, threeGramIndex.getThreeGramIndex());
    }
}