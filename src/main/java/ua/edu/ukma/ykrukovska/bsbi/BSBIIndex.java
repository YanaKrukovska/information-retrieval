package ua.edu.ukma.ykrukovska.bsbi;


import java.io.File;
import java.io.IOException;
import java.util.*;

import static ua.edu.ukma.ykrukovska.PathValues.BOOK_PATH;


public class BSBIIndex {

    private Queue<String[]> queue;
    private Map<String, Integer> termList;
    private HashMap<String, Integer> blockDocumentList;
    private HashMap<Integer, TermPostings> termPostingsLists;
    private List<File> documents;


    public BSBIIndex(Queue<String[]> queue, HashMap<String, Integer> termList, HashMap<String, Integer> blockDocumentList, File[] documents) {
        this.queue = queue;
        this.termList = termList;
        this.blockDocumentList = blockDocumentList;
        this.termPostingsLists = new HashMap<>();
        this.documents = new LinkedList<>(Arrays.asList(documents));
    }


    public void createIndex(int blockNumber, int filesPerBlock) {
        int blockID = blockNumber;
        int filesDone = 0;
        int termID = termList.size();
        boolean isIndexingFinished = false;


        while (!isIndexingFinished) {

            String[] document = queue.poll();
            if (document != null) {
                String documentTitle = document[0];
                String documentBody = document[1];

                blockDocumentList.put(documentTitle, blockID);


                String[] terms = documentBody.split(" ");

                for (String term : terms) {
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
                        termPostings.addPostingsList(documents.indexOf(new File(BOOK_PATH + documentTitle)));
                        termPostingsLists.put(id, termPostings);
                    } else {
                        termPostingsLists.get(id).addPostingsList(documents.indexOf(new File(BOOK_PATH + documentTitle)));
                    }
                }

                filesDone++;
            }

            if (filesDone == filesPerBlock) {
                writeBlockIntoFile(blockID);
                isIndexingFinished = true;
            }
        }
    }

    private void writeBlockIntoFile(int documentID) {
        BlockWriter blockWriter = new BlockWriter();
        try {
            blockWriter.write(termPostingsLists, documentID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}