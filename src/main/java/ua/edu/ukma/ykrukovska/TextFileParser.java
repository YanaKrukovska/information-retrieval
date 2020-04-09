package ua.edu.ukma.ykrukovska;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TextFileParser {

    private List<String> words = new LinkedList<>();

    public TextFileParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        Objects.requireNonNull(fileName, "File name ust be not null");
        readFile(fileName);
    }


    public void readFile(String fileName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String st;
        while ((st = bufferedReader.readLine()) != null) {
            words.addAll(Arrays.asList(st.split("[^a-zA-Zа-яА-ЯіІєЄїЇёЁ]+")));
        }


    }


    public List<String> getWords() {
        return words;
    }
}
