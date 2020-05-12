package ua.edu.ukma.ykrukovska;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextFileParser {

    private List<String> words = new LinkedList<>();
    private Set<String> uniqueWords = new TreeSet<>();

    public TextFileParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        Objects.requireNonNull(fileName, "File name ust be not null");
        readFile(fileName);
    }


    public void readFile(String fileName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String st;
        while ((st = bufferedReader.readLine()) != null) {
            uniqueWords.addAll(Arrays.asList(st.split("[^a-zA-Zа-яА-ЯіІєЄїЇёЁ]+")));
        }


    }

    public Set<String> getUniqueWords() {
        return uniqueWords;
    }

    public List<String> getWords() {
        return words;
    }
}
