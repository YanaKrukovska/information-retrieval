import org.junit.Assert;
import org.junit.Test;
import ua.edu.ukma.ykrukovska.DictionaryArray;

public class DictionaryArrayTest {

    @Test
    public void sizeInitial() {
        DictionaryArray dictionaryArray = new DictionaryArray();
        Assert.assertEquals(0, dictionaryArray.getWordsCount());
    }

    @Test
    public void sizeAfterAdded() {
        DictionaryArray dictionaryArray = new DictionaryArray();
        String[] words = {"cat", "dog"};
        dictionaryArray.add(words);
        Assert.assertEquals(4, dictionaryArray.size());
    }

    @Test
    public void sizeAfterAdded3() {
        DictionaryArray dictionaryArray = new DictionaryArray();
        String[] words = {"cat", "dog", "I", "love", "bender"};
        dictionaryArray.add(words);
        Assert.assertEquals(8, dictionaryArray.size());
    }

    @Test
    public void addFirstWord() {
        DictionaryArray dictionaryArray = new DictionaryArray();
        dictionaryArray.add("cat");
        Assert.assertEquals(1, dictionaryArray.getWordsCount());
    }

    @Test
    public void addSecondWord() {
        DictionaryArray dictionaryArray = new DictionaryArray();
        dictionaryArray.add("cat");
        dictionaryArray.add("dog");
        Assert.assertEquals(2, dictionaryArray.getWordsCount());
    }

    @Test
    public void addWordTwice() {
        DictionaryArray dictionaryArray = new DictionaryArray();
        dictionaryArray.add("cat");
        dictionaryArray.add("cat");
        Assert.assertEquals(1, dictionaryArray.getWordsCount());
    }

    @Test
    public void addWords() {
        DictionaryArray dictionaryArray = new DictionaryArray();
        dictionaryArray.add("cat");
        dictionaryArray.add("dog");
        dictionaryArray.add("bender");
        dictionaryArray.add("cat");
        Assert.assertEquals(3, dictionaryArray.getWordsCount());
        Assert.assertTrue( dictionaryArray.checkIfWordExists("cat"));
        Assert.assertTrue( dictionaryArray.checkIfWordExists("dog"));
        Assert.assertTrue( dictionaryArray.checkIfWordExists("bender"));
    }
}