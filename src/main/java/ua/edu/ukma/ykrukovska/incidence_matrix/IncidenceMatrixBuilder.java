package ua.edu.ukma.ykrukovska.incidence_matrix;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;
import ua.edu.ukma.ykrukovska.inverted_index.InvertedIndex;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class IncidenceMatrixBuilder {

    public IncidenceMatrix createCollection(List<String> files) {

        IncidenceMatrix matrix = new IncidenceMatrix(files);

        for (String fileName : files) {
            try {
                FictionBookParser fictionBookParser = new FictionBookParser(fileName);
                List<String> wordList = fictionBookParser.getWordList();
                for (String word : wordList) {
                    matrix.add(word.toLowerCase(), fileName);
                }

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }

        return matrix;
    }

}
