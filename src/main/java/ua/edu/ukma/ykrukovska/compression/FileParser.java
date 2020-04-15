package ua.edu.ukma.ykrukovska.compression;

import ua.edu.ukma.ykrukovska.FictionBookParser;

import java.io.File;
import java.util.*;

class FileParser {
    public Map<String, Integer> dictionary;
    public int documentLength = 0;

    public FileParser() {
    }


    public void parseFile(File file) throws Exception {
        dictionary = new TreeMap<>();
        FictionBookParser fictionBookParser = new FictionBookParser(file.getPath());
        List<String> wordList = fictionBookParser.getWordList();


        for (String word : wordList) {
            if (!word.equals("")) {
                addToDictionary(word.toLowerCase(), dictionary);
            }
        }

    }

    private void addToDictionary(String token, Map<String, Integer> dictionary) {

        documentLength++;
        if (dictionary.containsKey(token))
            dictionary.put(token, dictionary.get(token) + 1);
        else {
            dictionary.put(token, 1);
        }
    }

}