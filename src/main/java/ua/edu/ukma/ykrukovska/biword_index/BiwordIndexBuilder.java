package ua.edu.ukma.ykrukovska.biword_index;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public class BiwordIndexBuilder {

    private BiwordIndexBuilder() {
    }

    public static BiwordIndex createBiwordIndex(List<String> files) {

        BiwordIndex biwordIndex = new BiwordIndex(files);

        for (String fileName : files) {
            try {
                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> wordList = fictionBookParser.getWordList();

                if (wordList.size() > 1) {
                    int i = 0;
                    do {
                        String phrase = wordList.get(i).toLowerCase() + " " + wordList.get(++i).toLowerCase();
                        biwordIndex.add(fileName, phrase);
                    } while (i < wordList.size() - 1);
                } else {
                    biwordIndex.add(fileName, wordList.get(0));
                }
                System.out.println("DONE " + fileName);

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }

        return biwordIndex;
    }

}
