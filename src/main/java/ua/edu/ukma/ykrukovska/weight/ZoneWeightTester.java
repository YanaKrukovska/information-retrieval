package ua.edu.ukma.ykrukovska.weight;

import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;
import ua.edu.ukma.ykrukovska.bsbi.BSBI;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static ua.edu.ukma.ykrukovska.PathValues.FILES_AS_FILES_SHORT_2;
import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH_BSBI;

public class ZoneWeightTester {

    private List<File> files;
    private BSBI bsbi;

    private ZoneWeightTester(List<File> files) {
        this.files = files;
        this.bsbi = new BSBI((File[]) files.toArray());
        bsbi.doBSBI();


    }

    private List<PostingWeightZone> findDocuments(String word) throws IOException, ParserConfigurationException, SAXException {

        String searchWord = word.toLowerCase();
        List<PostingWeightZone> result = new LinkedList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(RESULT_PATH_BSBI + "bsbi_index.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split(":");
             if (bsbi.getIndexer().getTermList().get(searchWord) != null && bsbi.getIndexer().getTermList().get(searchWord) == Integer.parseInt(split[0])) {

                String[] docs = split[1].split(",");

                for (int i = 0; i < docs.length; i++) {
                    FictionBookParser fictionBookParser = new FictionBookParser(files.get(i).getPath());
                    PostingWeightZone resultDocument = new PostingWeightZone(Integer.parseInt(docs[i]), PostingWeightZone.ZoneScores.CONTENT);

                    String[] titleArr = fictionBookParser.getTitle().split(" ");
                    for (String title : titleArr) {
                        if (title.toLowerCase().equals(word)) {
                            resultDocument.addZone(PostingWeightZone.ZoneScores.TITLE);
                        }
                    }

                    String[] authorArr = fictionBookParser.getAuthor().split(" ");
                    for (String author : authorArr) {
                        if (author.toLowerCase().equals(word)) {
                            resultDocument.addZone(PostingWeightZone.ZoneScores.AUTHOR);
                        }
                    }
                    result.add(resultDocument);

                }


            }
        }


        return result;

    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        ZoneWeightTester zoneWeightTester = new ZoneWeightTester(FILES_AS_FILES_SHORT_2);

        int toContinue = 0;
        Scanner stringScanner = new Scanner(System.in);
        Scanner intScanner = new Scanner(System.in);
        String query;
        do {
            System.out.println("insert word: ");
            query = stringScanner.nextLine();

            List<PostingWeightZone> result = zoneWeightTester.findDocuments(query);

            for (PostingWeightZone aResult : result) {
                System.out.println("Document id: " + aResult.getId() + ", weight = " + aResult.getWeight());
            }
            System.out.println("Would you like to continue? 0 - no");
            toContinue = intScanner.nextInt();
        } while (toContinue != 0);
    }
}
