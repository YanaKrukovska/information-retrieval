package ua.edu.ukma.ykrukovska.bsbi;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class MergeIndexWriter {

    private final Queue<TermPostings> listToMerge;
    private final File file;
    private BufferedWriter writer;
    private final BSBI bsbi;

    public MergeIndexWriter(BSBI bsbi, Queue<TermPostings> list, File file) throws IOException {
        this.bsbi = bsbi;
        this.listToMerge = list;
        this.file = file;
        writer = new BufferedWriter(new FileWriter(this.file));
    }

    public void mergeAndWrite() throws IOException {
        ArrayList<TermPostings> termPostingsListsToMerge = new ArrayList<>();
        generateTermPostingsList(termPostingsListsToMerge);
    }

    private void generateTermPostingsList(ArrayList<TermPostings> termPostingsListsToMerge) throws IOException {

        boolean readersFinished = false;
        while (!readersFinished) {

            try {
                TermPostings termPostingsList = listToMerge.poll();
                if (termPostingsList != null) {
                    if (!termPostingsListsToMerge.isEmpty()) {
                        TermPostings previousTPL = termPostingsListsToMerge.get(termPostingsListsToMerge.size() - 1);
                        if (termPostingsList.getTermId() != previousTPL.getTermId()) {
                            TermPostings tpl = mergeTermPostingsLists(termPostingsListsToMerge);
                            writeTermPostingsList(tpl);
                            termPostingsListsToMerge.clear();
                            termPostingsListsToMerge.add(termPostingsList);
                        } else {
                            termPostingsListsToMerge.add(termPostingsList);
                        }
                    } else {
                        termPostingsListsToMerge.add(termPostingsList);
                    }
                } else {
                    throw new InterruptedException();
                }
            } catch (InterruptedException e) {

                if (bsbi.getBlocksFinishedCounter()) {
                    writer.close();
                    readersFinished = true;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private TermPostings mergeTermPostingsLists(ArrayList<TermPostings> termPostingsLists) {

        int termID = termPostingsLists.get(0).getTermId();

        Queue<Integer> docIDs = new PriorityQueue<>();
        for (TermPostings termPostingsList : termPostingsLists) {
            docIDs.addAll(termPostingsList.getPostingsList());
        }

        List<Integer> postingsList = new ArrayList<>(docIDs.size());
        while (!docIDs.isEmpty()) {
            postingsList.add(docIDs.remove());
        }

        return new TermPostings(termID, postingsList);
    }

    private void writeTermPostingsList(TermPostings termPostingsList) throws IOException {

        List<Integer> postingsList = new ArrayList<>(termPostingsList.getPostingsList());
        StringBuilder line = new StringBuilder();
        line.append(termPostingsList.getTermId()).append(":");

        for (int docId : postingsList) {
            line.append(docId).append(",");
        }

        writer.write(line.toString());
        writer.newLine();

    }




}