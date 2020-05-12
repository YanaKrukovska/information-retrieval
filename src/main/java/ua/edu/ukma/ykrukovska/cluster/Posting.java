package ua.edu.ukma.ykrukovska.cluster;


import java.util.ArrayList;
import java.util.List;


public class Posting {
    private int documentID;
    private List<Integer> mPositionIds = new ArrayList<>();
    private int termFrequency;
    private int inverseTermFrequency;


    public Posting(int documentId, int termFrequency, List<Integer> positions) {
        this.termFrequency = termFrequency;
        this.documentID = documentId;
        this.mPositionIds = positions;
    }

    public Posting(int documentId) {
        documentID = documentId;
    }

    public Posting(int documentId, int termFrequency) {
        this.documentID = documentId;
        this.termFrequency = termFrequency;
    }

    public int getTermFrequency() {
        return termFrequency;
    }

    public void setTermFrequency(int mTermFrequency) {
        this.termFrequency = mTermFrequency;
    }

    public void addPostingPosition(int position) {
        mPositionIds.add(position);



    }

    public int getDocumentId() {
        return documentID;
    }

    public List<Integer> getPositionsInDoc() {
        return mPositionIds;
    }
}