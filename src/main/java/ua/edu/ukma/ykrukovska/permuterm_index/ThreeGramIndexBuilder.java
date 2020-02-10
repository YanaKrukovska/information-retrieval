package ua.edu.ukma.ykrukovska.permuterm_index;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class ThreeGramIndexBuilder {

    public static ThreeGramIndex createCollection(List<String> files) {

        ThreeGramIndex threeGramIndex = new ThreeGramIndex(files);

        for (String fileName : files) {
            try {
                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> wordList = fictionBookParser.getWordList();
                for (String word : wordList) {
                    threeGramIndex.add(word.toLowerCase());
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }

        return threeGramIndex;
    }

}
