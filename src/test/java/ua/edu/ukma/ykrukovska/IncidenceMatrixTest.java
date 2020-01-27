package ua.edu.ukma.ykrukovska;

import org.junit.Assert;
import org.junit.Test;
import ua.edu.ukma.ykrukovska.incidence_matrix.IncidenceMatrix;

import java.util.Arrays;
import java.util.Collections;

public class IncidenceMatrixTest {


    @Test
    public void addFirstWord() {

        IncidenceMatrix matrix = new IncidenceMatrix(Collections.singletonList("1"));
        matrix.add("cat", "1");
        Assert.assertEquals(1, matrix.getMatrix().size());
        Assert.assertTrue( matrix.getMatrix().get("cat").get(0));

    }

    @Test
    public void addTwoSameWords() {

        IncidenceMatrix matrix = new IncidenceMatrix(Collections.singletonList("1"));
        matrix.add("cat", "1");
        matrix.add("cat", "1");
        Assert.assertEquals(1, matrix.getMatrix().size());
        Assert.assertTrue( matrix.getMatrix().get("cat").get(0));

    }

    @Test
    public void addTwoWords() {

        IncidenceMatrix matrix = new IncidenceMatrix(Collections.singletonList("1"));
        matrix.add("cat", "1");
        matrix.add("dog", "1");
        Assert.assertEquals(2, matrix.getMatrix().size());
        Assert.assertTrue( matrix.getMatrix().get("cat").get(0));
        Assert.assertTrue( matrix.getMatrix().get("dog").get(0));

    }

    @Test
    public void addTwoWordsTwoFiles() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("1", "2"));
        matrix.add("cat", "1");
        matrix.add("dog", "2");
        Assert.assertEquals(2, matrix.getMatrix().size());
        Assert.assertTrue( matrix.getMatrix().get("cat").get(0));
        Assert.assertTrue( matrix.getMatrix().get("dog").get(1));

    }

    @Test
    public void addThreeWordsTwoFiles() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("1", "2"));
        matrix.add("cat", "1");
        matrix.add("dog", "2");
        matrix.add("duck", "1");
        Assert.assertEquals(3, matrix.getMatrix().size());
        Assert.assertTrue( matrix.getMatrix().get("cat").get(0));
        Assert.assertTrue( matrix.getMatrix().get("dog").get(1));
        Assert.assertTrue( matrix.getMatrix().get("duck").get(0));

    }
}