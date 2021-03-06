package ua.edu.ukma.ykrukovska.dictionary;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CollectionBuilder {

    private CollectionBuilder() {
    }

    public static Collection createCollection(List<String> files) {

        Collection collection = new Collection();

       // Dictionary dictionary = new DictionaryArray();
      Dictionary dictionary = new DictionarySet();

        for (String fileName : files) {
            try {
                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> wordList = fictionBookParser.getWordList();
                for (String word : wordList) {
                    dictionary.add(word);
                }
                File file = new File(fileName);
                collection.getItems().add(new CollectionItem(fileName, file.length(), wordList.size()));
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }
      //  System.out.println("Elapsed time: " + ((System.nanoTime() - startTime)/1000));
        collection.setDictionary(dictionary);
        return collection;
    }

}
