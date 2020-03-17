package ua.edu.ukma.ykrukovska.bsbi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class BlockIndexReader {

    private Queue<TermPostings> listToMerge;
    private BufferedReader bufferedReader;
    private final BSBI bsbi;

    public BlockIndexReader(BSBI bsbi, Queue<TermPostings> list, File file) throws IOException {
        this.bsbi = bsbi;
        this.listToMerge = list;
        this.bufferedReader = new BufferedReader(new FileReader(file));
    }

    public void readBlockIndex() {
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                TermPostings termPostingsList = generateTermPostingsList(line);
                listToMerge.add(termPostingsList);
            }
            bsbi.incrementBlockFinishedCounter();
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private TermPostings generateTermPostingsList(String line) {

        String[] splitSemiColon = line.split(":");
        int termID = Integer.parseInt(splitSemiColon[0]);
        String postingsString = splitSemiColon[1];

        String[] postingsArray = postingsString.split(",");
        List<Integer> postingsList = new ArrayList<>();
        for (String posting : postingsArray) {
            postingsList.add(Integer.parseInt(posting));
        }

        return new TermPostings(termID, postingsList);
    }
}