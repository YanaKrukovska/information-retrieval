package ua.edu.ukma.ykrukovska.compression;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

public class CompressedDictionaryBuilder {

    private static TreeSet<String> termList = new TreeSet<>();

    private CompressedDictionaryBuilder() {
    }

    private static String[] resize(String[] arr, int newSize) {
        String[] copy = new String[newSize];
        for (int i = 0; i < newSize && i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public static CompressedDictionary createCompressedDictionary(List<String> files) {
        String[] terms = new String[10];
        int termsIndex = 0;

        for (String fileName : files) {
            try {
                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> wordList = fictionBookParser.getWordList();
                for (String word : wordList) {

                    if (!termList.contains(word)){
                        termList.add(word);
                    if (termsIndex >= terms.length) {
                        terms = resize(terms, terms.length * 2);
                    }
                    terms[termsIndex++] = word;
                    terms = resize(terms, termsIndex);
                    }
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }


        }

        return new CompressedDictionary(terms);
    }
}
