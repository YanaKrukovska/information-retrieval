package ua.edu.ukma.ykrukovska.permuterm_index;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PermutermIndexTest {

    @Test
    public void addOneWord() {
        PermutermIndex permutermIndex = new PermutermIndex(Collections.singletonList("1"));
        permutermIndex.generateRotations("cat");


        Map<String, String> result = new HashMap<>();
        result.put("cat$", "cat");
        result.put("at$c", "cat");
        result.put("t$ca", "cat");
        result.put("$cat", "cat");
        Assert.assertEquals(result, permutermIndex.getPermutermIndex());

    }

    @Test
    public void addTwoWords() {
        PermutermIndex permutermIndex = new PermutermIndex(Collections.singletonList("1"));
        permutermIndex.generateRotations("cat");
        permutermIndex.generateRotations("kitten");


        Map<String, String> result = new HashMap<>();
        result.put("cat$", "cat");
        result.put("at$c", "cat");
        result.put("t$ca", "cat");
        result.put("$cat", "cat");
        result.put("kitten$", "kitten");
        result.put("itten$k", "kitten");
        result.put("tten$ki", "kitten");
        result.put("ten$kit", "kitten");
        result.put("en$kitt", "kitten");
        result.put("n$kitte", "kitten");
        result.put("$kitten", "kitten");

        Assert.assertEquals(result, permutermIndex.getPermutermIndex());

    }
}