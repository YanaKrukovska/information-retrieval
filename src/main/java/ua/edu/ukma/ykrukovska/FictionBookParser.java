package ua.edu.ukma.ykrukovska;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class FictionBookParser {

    private List<String> words = new LinkedList<>();
    private String title = "";
    private String author = "";


    public FictionBookParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        Objects.requireNonNull(fileName, "File name ust be not null");

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        FB2Handler handler = new FB2Handler();
        saxParser.parse(fileName, handler);
    }

    public List<String> getWordList() {
        return words;
    }


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public class FB2Handler extends DefaultHandler {
        private static final String PARAGRAPH = "p";
        private static final String AUTHOR = "author";
        private static final String TITLE = "title";


        private String elementValue;

        @Override
        public void characters(char[] ch, int start, int length) {
            elementValue = new String(ch, start, length);
        }


        @Override
        public void endElement(String uri, String localName, String qName) {
            switch (qName) {
                case PARAGRAPH:
                    String[] splitWords = elementValue.split("[^a-zA-Zа-яА-ЯіІєЄїЇёЁ]+");
                    words.addAll(Arrays.asList(splitWords));
                    break;
                case AUTHOR:
                    author = elementValue;
                    break;
                case TITLE:
                    title = elementValue;
                    break;
            }
        }

    }
}