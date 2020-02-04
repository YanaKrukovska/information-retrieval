package ua.edu.ukma.ykrukovska.biword_index;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class BiwordIndexBuilder {

    public BiwordIndex createBiwordIndex(List<String> files) {

        BiwordIndex biwordIndex = new BiwordIndex(files);

        for (String fileName : files) {
            try {
                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> wordList = fictionBookParser.getWordList();

                if (wordList.size() > 2) {
                    int i = 0;
                    do {
                        String phrase = wordList.get(i).toLowerCase() + " " + wordList.get(++i).toLowerCase();
                        biwordIndex.add(fileName, phrase);
                    } while (i < wordList.size() - 1);
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }

        return biwordIndex;
    }

}
