package ua.edu.ukma.ykrukovska.bsbi;


import org.xml.sax.SAXException;
import ua.edu.ukma.ykrukovska.FictionBookParser;
import ua.edu.ukma.ykrukovska.TextFileParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class BlockReader {

    private File[] blockFiles;
    private BlockingQueue<Document> queue;

    public BlockReader(File[] blockFiles, BlockingQueue<Document> queue) {
        this.queue = queue;
        this.blockFiles = blockFiles;
    }


    public void readBlocks() {

        for (File textFile : blockFiles) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile));
                List<String> wordList;
                Set<String> uniqueWord;
                String textPath = textFile.getPath();

                Document document;
                if (textPath.split("\\.")[1].equals("fb2")) {

                    FictionBookParser fictionBookParser = new FictionBookParser(textFile.getPath());
                     wordList = fictionBookParser.getWordList();
                   document = new Document(textFile.getName(), wordList);

                } else {

                    TextFileParser textFileParser = new TextFileParser(textFile.getPath());
                    //wordList = textFileParser.getWords();
                    uniqueWord = textFileParser.getUniqueWords();
                    document = new Document(textFile.getName(), uniqueWord);

                }



                queue.add(document);
                bufferedReader.close();


            } catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        }


    }
}