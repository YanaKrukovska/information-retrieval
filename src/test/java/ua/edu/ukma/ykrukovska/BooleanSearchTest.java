package ua.edu.ukma.ykrukovska;

import org.junit.Assert;
import org.junit.Test;
import ua.edu.ukma.ykrukovska.incidence_matrix.IncidenceMatrix;

import java.util.*;

public class BooleanSearchTest {

    @Test
    public void findWordMatrix() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("1", "2", "3"));
        matrix.add("cat", "1");
        matrix.add("cat", "2");
        matrix.add("dog", "1");
        matrix.add("dog", "3");

        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        Assert.assertEquals(2, booleanSearcher.findWord("cat").size());
        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(1);
        Assert.assertEquals(resultsList, booleanSearcher.findWord("cat"));
    }


    @Test
    public void findWordMatrix2() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("1", "2", "3"));
        matrix.add("cat", "1");
        matrix.add("cat", "2");
        matrix.add("dog", "2");
        matrix.add("dog", "3");

        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        Assert.assertEquals(2, booleanSearcher.findWord("dog").size());
        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(1);
        resultsList.add(2);
        Assert.assertEquals(resultsList, booleanSearcher.findWord("dog"));
    }

    @Test
    public void findTwoWordsOneFile() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2"));
        matrix.add("cat", "0");
        matrix.add("cat", "1");
        matrix.add("dog", "1");
        matrix.add("dog", "2");

        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(1);
        Assert.assertEquals(resultsList, booleanSearcher.findTwoWords("dog", "cat"));
    }

    @Test
    public void findTwoWordsTwoFiles() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2"));
        matrix.add("cat", "0");
        matrix.add("cat", "1");
        matrix.add("cat", "2");
        matrix.add("dog", "1");
        matrix.add("dog", "2");
        matrix.add("mouse", "2");
        matrix.add("horse", "1");
        matrix.add("horse", "2");

        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(1);
        resultsList.add(2);
        Assert.assertEquals(resultsList, booleanSearcher.findTwoWords("dog", "cat"));
    }


    @Test
    public void findOneWordExcludeAnother() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2"));
        matrix.add("cat", "0");
        matrix.add("cat", "1");
        matrix.add("cat", "2");matrix.add("dog", "1");
        matrix.add("dog", "2");

        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        Assert.assertEquals(resultsList, booleanSearcher.findOneWordWithoutAnother("cat", "dog"));
    }


    @Test
    public void findOneWordExcludeAnotherTwoFiles() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2", "3"));
        matrix.add("cat", "0");
        matrix.add("cat", "1");
        matrix.add("cat", "2");
        matrix.add("cat", "3");
        matrix.add("dog", "1");
        matrix.add("dog", "2");


        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(3);
        Assert.assertEquals(resultsList, booleanSearcher.findOneWordWithoutAnother("cat", "dog"));
    }


    @Test
    public void findOneWordExcludeAnotherThreeFiles() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2", "3"));
        matrix.add("dog", "2");
        matrix.add("mouse", "2");
        matrix.add("horse", "1");
        matrix.add("horse", "2");
        matrix.add("cat", "0");
        matrix.add("cat", "1");
        matrix.add("cat", "2");
        matrix.add("cat", "3");


        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(1);
        resultsList.add(3);
        Assert.assertEquals(resultsList, booleanSearcher.findOneWordWithoutAnother("cat", "mouse"));
    }

    @Test
    public void findOneWordExcludeAnotherThreeFiles2() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2", "3"));
        matrix.add("dog", "2");
        matrix.add("mouse", "2");
        matrix.add("horse", "1");
        matrix.add("horse", "2");
        matrix.add("cat", "0");
        matrix.add("cat", "1");
        matrix.add("cat", "2");
        matrix.add("cat", "3");


        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(1);
        Assert.assertEquals(resultsList, booleanSearcher.findOneWordWithoutAnother("horse", "mouse"));
    }


    @Test
    public void findOneWordExcludeAnotherThreeFiles3() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2", "3", "4"));
        matrix.add("dog", "2");
        matrix.add("mouse", "4");
        matrix.add("horse", "1");
        matrix.add("horse", "2");
        matrix.add("cat", "0");
        matrix.add("cat", "1");
        matrix.add("cat", "2");
        matrix.add("cat", "3");


        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(4);
        Assert.assertEquals(resultsList, booleanSearcher.findOneWordWithoutAnother("mouse", "cat"));
    }

    @Test
    public void findOneWordOrAnother() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2"));
        matrix.add("dog", "2");
        matrix.add("cat", "0");
        matrix.add("cat", "1");

        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(1);
        resultsList.add(2);
        Assert.assertEquals(resultsList, booleanSearcher.findOneWordOrAnother("cat", "dog"));
    }

    @Test
    public void findOneWordOrAnotherAppearInSameFile() {

        IncidenceMatrix matrix = new IncidenceMatrix(Arrays.asList("0", "1", "2", "3"));
        matrix.add("mouse", "3");
        matrix.add("dog", "2");
        matrix.add("cat", "0");
        matrix.add("horse", "2");
        matrix.add("cat", "1");
        matrix.add("cat", "2");

        BooleanSearcher booleanSearcher = new BooleanSearcher(matrix);

        List<Integer> resultsList = new LinkedList<>();
        resultsList.add(0);
        resultsList.add(1);
        resultsList.add(2);
        Assert.assertEquals(resultsList, booleanSearcher.findOneWordOrAnother("cat", "dog"));
    }


}