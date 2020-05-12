package ua.edu.ukma.ykrukovska.cluster;


import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import static ua.edu.ukma.ykrukovska.PathValues.BOOK_PATH_BSBI_2;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH;

public class ClusterTester {


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        PositionalInvertedIndex index = new PositionalInvertedIndex();

        int termCount = 0;

        File source = new File(BOOK_PATH_BSBI_2);


        int docId = 0;

        List<File> files = new ArrayList(Arrays.asList(source.listFiles()));
        for (File document : Objects.requireNonNull(source.listFiles())) {

            FictionBookParser fictionBookParser = new FictionBookParser(document.getPath());

            int position = 0;

            for (String term : fictionBookParser.getWordList()) {

                if (!term.equals("")) {
                    termCount++;
                    index.addTerm(term.toLowerCase(), docId, position++, fictionBookParser.getWordList().size());
                }

            }
            docId++;

        }

        File file = new File(RESULT_PATH + "Clustering.txt");
        ClusterPruningIndex clusterPruningIndex = new ClusterPruningIndex();
        clusterPruningIndex.buildIndex(source.listFiles(), index);
        clusterPruningIndex.writeToDisk(Paths.get(file.getPath()));


        String term[] = {"wonderland", "in", "girl"};

        System.out.println("Documents found: ");
        for (Integer p : clusterPruningIndex.getPostings(term)) {
            System.out.println(files.get(p).getName());

        }


    }
}