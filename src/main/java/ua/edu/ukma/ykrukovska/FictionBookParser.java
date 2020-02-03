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

    private SAXParserFactory factory;
    private SAXParser saxParser;
    private FB2Handler handler;
    private List<String> words = new LinkedList<>();

    public FictionBookParser(String fileName) throws ParserConfigurationException, SAXException, IOException {
        Objects.requireNonNull(fileName, "File name ust be not null");

        this.factory = SAXParserFactory.newInstance();
        this.saxParser = factory.newSAXParser();
        this.handler = new FB2Handler();
        saxParser.parse(fileName, handler);
    }

    public List<String> getWordList() {
        return words;
    }


    public class FB2Handler extends DefaultHandler {
        private static final String PARAGRAPH = "p";


        private String elementValue;

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            elementValue = new String(ch, start, length);
        }


        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            switch (qName) {
                case PARAGRAPH:
                  //  String[] splitWords = elementValue.split("[^a-zA-Zа-яА-ЯіІєЄїЇёЁ0-9'\\+-]+[^a-zA-Zа-яА-ЯіІєЄїЇёЁ]*");
                    String[] splitWords = elementValue.split("[^a-zA-Zа-яА-ЯіІєЄїЇёЁ]+");
                    words.addAll(Arrays.asList(splitWords));
                    break;
            }
        }


    }
}