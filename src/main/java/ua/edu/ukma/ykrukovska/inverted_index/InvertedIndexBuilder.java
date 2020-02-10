package ua.edu.ukma.ykrukovska.inverted_index;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class InvertedIndexBuilder {

    private InvertedIndexBuilder() {
    }

    public static InvertedIndex createCollection(List<String> files) {

        InvertedIndex invertedIndex = new InvertedIndex(files);

        for (String fileName : files) {
            int wordPosition = 1;
            try {
                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> wordList = fictionBookParser.getWordList();
                for (String word : wordList) {
                    invertedIndex.add(word.toLowerCase(), fileName, wordPosition++);
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }

        return invertedIndex;
    }

}
