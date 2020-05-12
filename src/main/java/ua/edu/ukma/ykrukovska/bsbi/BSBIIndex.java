package ua.edu.ukma.ykrukovska.bsbi;


import java.io.File;
import java.io.IOException;
import java.util.*;

import static ua.edu.ukma.ykrukovska.bsbi.BSBITester.GUTENBERG_PATH;


public class BSBIIndex {

    private Queue<Document> queue;
    private BSBI bsbi;
    private Map<String, Integer> termList;
    private HashMap<String, Integer> blockDocumentList;
    private HashMap<Integer, TermPostings> termPostingsLists;
    private List<File> documents;


    public BSBIIndex(BSBI bsbi, Queue<Document> queue, HashMap<String, Integer> termList, HashMap<String, Integer> blockDocumentList, File[] documents) {
        this.bsbi = bsbi;
        this.queue = queue;
        this.termList = termList;
        this.blockDocumentList = blockDocumentList;
        this.termPostingsLists = new HashMap<>();
        this.documents = new LinkedList<>(Arrays.asList(documents));
    }


    public void createIndex(int blockNumber, int filesPerBlock) {
        int filesDone = 0;
        int termID = termList.size();
        boolean isIndexingFinished = false;


        while (!isIndexingFinished) {

            Document document = queue.poll();
            if (document != null) {
                String documentTitle = document.getName();
                // List<String> documentBody = document.getWords();

                Set<String> body = document.getUniqueWords();
                blockDocumentList.put(documentTitle, blockNumber);


                for (String term : body) {
                    term = term.toLowerCase();
                    int id;
                    if (!termList.containsKey(term)) {
                        termList.put(term, termID);
                        id = termID;
                        termID++;
                    } else {
                        id = termList.get(term);
                    }

                    if (!termPostingsLists.containsKey(id)) {
                        TermPostings termPostings = new TermPostings(id, new ArrayList<>());
                        termPostings.addPostingsList(documents.indexOf(new File(GUTENBERG_PATH + "//" + documentTitle)));
                        termPostingsLists.put(id, termPostings);
                    } else {
                        termPostingsLists.get(id).addPostingsList(documents.indexOf(new File(GUTENBERG_PATH + "//" + documentTitle)));
                    }
                }

                filesDone++;
            }

            if (filesDone == filesPerBlock) {
                writeBlockIntoFile(blockNumber);
                isIndexingFinished = true;
            }
        }
    }

    private void writeBlockIntoFile(int documentID) {
        BlockWriter blockWriter = new BlockWriter();
        try {
            blockWriter.write(termPostingsLists, documentID);
            bsbi.getGeneralList().putAll(termPostingsLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, Integer> getTermList() {
        return termList;
    }


}