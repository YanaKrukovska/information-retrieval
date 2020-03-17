package ua.edu.ukma.ykrukovska.bsbi;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static ua.edu.ukma.ykrukovska.PathValues.RESULT_PATH_BSBI;

public class BlockWriter {

    public BlockWriter() {
    }

    public void write(HashMap<Integer, TermPostings> termPostingsLists, int blockNumber) throws IOException {
        File file = new File(RESULT_PATH_BSBI + "block" + blockNumber + ".txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        Set<Integer> termsIDs = termPostingsLists.keySet();
        for (Integer termID : termsIDs) {
            List<Integer> postingsList = new ArrayList<>(termPostingsLists.get(termID).getPostingsList());

            StringBuilder line = new StringBuilder();
            line.append(termID).append(":");

            for (int documentID : postingsList) {
                line.append(documentID).append(",");
            }

            bufferedWriter.write(line.toString());
            bufferedWriter.newLine();

        }
        bufferedWriter.close();

    }

}