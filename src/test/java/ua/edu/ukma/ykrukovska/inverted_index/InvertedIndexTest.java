package ua.edu.ukma.ykrukovska.inverted_index;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class InvertedIndexTest {

    @Test
    public void addOneWord() {
        InvertedIndex invertedIndex = new InvertedIndex(Collections.singletonList("1"));
        invertedIndex.add("cat", "1", 1);
        Assert.assertEquals(1, invertedIndex.getInvertedIndex().size());
        Assert.assertEquals( new LinkedList<>(Collections.singletonList(1)), invertedIndex.getInvertedIndex().get("cat").get("1"));

    }

    @Test
    public void addOneWordTwice() {
        InvertedIndex invertedIndex = new InvertedIndex(Collections.singletonList("1"));
        invertedIndex.add("cat", "1", 1);
        invertedIndex.add("cat", "1", 2);
        Assert.assertEquals(1, invertedIndex.getInvertedIndex().size());
        Assert.assertEquals(new LinkedList<>(Arrays.asList(1, 2)), invertedIndex.getInvertedIndex().get("cat").get("1"));
    }

    @Test
    public void addTwoWords() {
        InvertedIndex invertedIndex = new InvertedIndex(Collections.singletonList("1"));
        invertedIndex.add("cat", "1", 1);
        invertedIndex.add("dog", "1", 2);
        Assert.assertEquals(2, invertedIndex.getInvertedIndex().size());
        Assert.assertEquals(new LinkedList<>(Collections.singletonList(2)), invertedIndex.getInvertedIndex().get("dog").get("1"));
        Assert.assertEquals(new LinkedList<>(Collections.singletonList(1)), invertedIndex.getInvertedIndex().get("cat").get("1"));
    }

    @Test
    public void addTwoWordsDiffDocuments() {
        InvertedIndex invertedIndex = new InvertedIndex(Collections.singletonList("1"));
        invertedIndex.add("cat", "1", 1);
        invertedIndex.add("dog", "1", 2);
        invertedIndex.add("dog", "2", 4);
        invertedIndex.add("dog", "1", 4);
        Assert.assertEquals(2, invertedIndex.getInvertedIndex().size());
        Assert.assertEquals(new LinkedList<>(Collections.singletonList(1)), invertedIndex.getInvertedIndex().get("cat").get("1"));
        Assert.assertEquals(new LinkedList<>(Arrays.asList(2, 4)), invertedIndex.getInvertedIndex().get("dog").get("1"));
        Assert.assertEquals(new LinkedList<>(Arrays.asList(4)), invertedIndex.getInvertedIndex().get("dog").get("2"));
    }
}