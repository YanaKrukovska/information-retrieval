package ua.edu.ukma.ykrukovska.wild_card_search;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class SufixTreeBuilder {

    private SufixTreeBuilder() {
    }

    public static SuffixTree createSuffixTree(List<String> files) {

        SuffixTree tree = new SuffixTree();

        for (String fileName : files) {
            try {

                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> words = fictionBookParser.getWordList();
                for (String word : words) {
                    tree.add(word.toLowerCase());
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }


        return tree;


    }
}